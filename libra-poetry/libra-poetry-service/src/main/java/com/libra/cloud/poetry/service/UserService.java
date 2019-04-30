package com.libra.cloud.poetry.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.poetry.model.UserModel;
import com.libra.cloud.poetry.entity.User;
import com.libra.cloud.poetry.exception.PoetryExceptionEnum;
import com.libra.cloud.poetry.mapper.UserMapper;
import com.libra.core.exception.ServiceException;
import com.libra.core.jwt.utils.JwtTokenUtil;
import com.libra.core.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
        String md5Hex = ToolUtil.md5Hex(password + user.getSalt());
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
    public UserModel getLoginUserByToken(String token) {
        UserModel userModel = new UserModel();
        try {
            int userId = Integer.valueOf(jwtTokenUtil.getUserIdFromToken(token));
            User user = selectById(userId);
//            user = jwtTokenUtil.getClaimFromToken(token).get(PoetryConstants.LOGIN_USER_CACHE_PREFIX, User.class);
            BeanUtil.copyProperties(user, userModel);
            userModel.setToken(token);
        } catch (Exception e) {
            throw new ServiceException(PoetryExceptionEnum.TOKEN_ERROR);
        }
        return userModel;
    }
}
