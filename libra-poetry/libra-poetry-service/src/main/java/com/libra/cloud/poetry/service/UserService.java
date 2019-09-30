package com.libra.cloud.poetry.service;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.poetry.entity.Follow;
import com.libra.cloud.poetry.entity.User;
import com.libra.cloud.poetry.entity.UserThird;
import com.libra.cloud.poetry.exception.PoetryExceptionEnum;
import com.libra.cloud.poetry.mapper.UserMapper;
import com.libra.cloud.poetry.model.UserModel;
import com.libra.core.exception.ServiceException;
import com.libra.core.jwt.utils.JwtTokenUtil;
import com.libra.core.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserThirdService userThirdService;
    @Autowired
    private FollowService followService;

    /**
     * 用户登录，登录成功返回token
     */
    public UserModel login(String username, String password) {

        //查询账号是否存在
        User user = null;
        List<User> accounts = this.selectList(new EntityWrapper<User>().eq("account", username));
        if (accounts != null && accounts.size() > 0) {
            user = accounts.get(0);
        } else {
            throw new ServiceException(PoetryExceptionEnum.USER_NOT_FOUND);
        }

        //校验账号密码是否正确
        String md5Hex = ToolUtil.md5Hex(password, user.getSalt());
        if (!md5Hex.equals(user.getPassword())) {
            throw new ServiceException(PoetryExceptionEnum.INVALID_PWD);
        }

        //生成token
        HashMap<String, Object> claims = new HashMap<>();
//        claims.put(PoetryConstants.LOGIN_USER_CACHE_PREFIX, user);
        String token = jwtTokenUtil.generateToken(user.getId().toString(), claims);
        UserModel userModel = new UserModel();
        BeanUtil.copyProperties(user, userModel);
        userModel.setToken(token);
        List<UserThird> list = userThirdService.selectList(new EntityWrapper<UserThird>().eq("uid", user.getId()));
        userModel.setThirdInfoList(list);
        return userModel;
    }

    /**
     * 校验token是否正确
     */
    public boolean checkToken(String token) {
        //先校验jwt是否正确
        if (!jwtTokenUtil.checkToken(token)) {
            return false;
        }
        return !jwtTokenUtil.isTokenExpired(token);
    }

    /**
     * 退出登录
     */
    public void logout() {
    }

    /**
     * 获取登录用户通过token
     */
    public UserModel getUserInfo(String token, int userId) {
        UserModel userModel = new UserModel();
        try {
            User user = selectById(userId);
//            user = jwtTokenUtil.getClaimFromToken(token).get(PoetryConstants.LOGIN_USER_CACHE_PREFIX, User.class);
            BeanUtil.copyProperties(user, userModel);
            userModel.setToken(token);
            List<UserThird> list = userThirdService.selectList(new EntityWrapper<UserThird>().eq("uid", user.getId()));
            userModel.setThirdInfoList(list);
            int followNum = followService.selectCount(new EntityWrapper<Follow>().eq("uid", user.getId()).and().eq("status", 1));
            userModel.setFollow(followNum);
            int fansNum = followService.selectCount(new EntityWrapper<Follow>().eq("followed_id", user.getId()).and().eq("status", 1));
            userModel.setFans(fansNum);
        } catch (Exception e) {
            throw new ServiceException(PoetryExceptionEnum.TOKEN_ERROR);
        }
        return userModel;
    }

    public UserModel wxLogin(String code, String nickName, String avatarUrl, String province, String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxd612e795c9823faa&secret=5917e731fa39a1226b488528b8a7c8da&js_code=" + code + "&grant_type=authorization_code";
        String content = restTemplate.getForObject(url, String.class);
        JSONObject body = JSON.parseObject(content);
        String openid = null;
        if (body != null) {
            openid = body.getString("openid");
        }
        if (StringUtils.isEmpty(openid)) {
            throw new ServiceException(PoetryExceptionEnum.TEMPTY_OPENID);
        }

        UserThird userThird = userThirdService.selectOne(new EntityWrapper<UserThird>().eq("third_key", openid));
        if (userThird == null) {
            User user = new User();
            user.setAccount(openid);
            user.setPassword(ToolUtil.getRandomString(6));
            user.setSalt(ToolUtil.getSalt());
            user.setAvatar(avatarUrl);
            user.setNickname(nickName);
            user.setCreateTime(new Date());
            user.setStatus(1);
            insert(user);
            //
            userThird = new UserThird();
            userThird.setThirdKey(openid);
            userThird.setThirdType(1);
            userThird.setUid(user.getId());
            userThirdService.insert(userThird);

            //生成token
            HashMap<String, Object> claims = new HashMap<>();
            String token = jwtTokenUtil.generateToken(user.getId().toString(), claims);
            UserModel userModel = new UserModel();
            BeanUtil.copyProperties(user, userModel);
            userModel.setToken(token);

            List<UserThird> list = new ArrayList<>();
            list.add(userThird);
            userModel.setThirdInfoList(list);
            return userModel;
        } else {
            User user = selectById(userThird.getUid());
            if (user == null) {
                user = new User();
                user.setAccount(openid);
                user.setPassword(ToolUtil.getRandomString(6));
                user.setSalt(ToolUtil.getSalt());
                user.setAvatar(avatarUrl);
                user.setNickname(nickName);
                user.setCreateTime(new Date());
                user.setStatus(1);
                insert(user);
            }

            //生成token
            HashMap<String, Object> claims = new HashMap<>();
            String token = jwtTokenUtil.generateToken(user.getId().toString(), claims);
            UserModel userModel = new UserModel();
            BeanUtil.copyProperties(user, userModel);
            userModel.setToken(token);

            List<UserThird> list = userThirdService.selectList(new EntityWrapper<UserThird>().eq("uid", user.getId()));
            userModel.setThirdInfoList(list);
            return userModel;
        }
    }
}
