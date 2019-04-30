package com.libra.cloud.poetry.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.libra.cloud.poetry.entity.Favorite;
import com.libra.cloud.poetry.exception.PoetryExceptionEnum;
import com.libra.cloud.poetry.service.FavoriteService;
import com.libra.core.constants.Constants;
import com.libra.core.jwt.utils.JwtTokenUtil;
import com.libra.core.reqres.request.RequestData;
import com.libra.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Libra
 * @date 2018/12/7
 * @description
 */
@RestController
@RequestMapping(name = "收藏管理", path = "/poetry/favorite")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(name = "获取用户的收藏列表", path = "/list")
    public ResponseData getUserFavoritePage(RequestData requestData) {
        return ResponseData.success(favoriteService.selectUserFavoritePage(Integer.valueOf(jwtTokenUtil.getUserIdFromToken(requestData.getString(Constants.REQUEST_TOKEN_PARAM)))));
    }

    @RequestMapping(name = "用户是否收藏", path = "/check")
    public ResponseData check(RequestData requestData) {
        Integer userId = Integer.valueOf(jwtTokenUtil.getUserIdFromToken(requestData.getString(Constants.REQUEST_TOKEN_PARAM)));
        Integer poetryId = requestData.getParam().getInteger("poetryId");
        return ResponseData.success(favoriteService.checkUserFavByPoetryId(userId, poetryId));
    }

    @RequestMapping(name = "添加收藏", path = "/add")
    public ResponseData add(RequestData requestData) {
        Integer userId = Integer.valueOf(jwtTokenUtil.getUserIdFromToken(requestData.getString(Constants.REQUEST_TOKEN_PARAM)));
        Integer poetryId = requestData.getParam().getInteger("poetryId");
        Favorite favorite = favoriteService.selectFavorite(userId, poetryId);
        if (favorite == null) {
            favorite = new Favorite();
        }
        favorite.setPoetryId(requestData.getParam().getInteger("poetryId"));
        favorite.setUid(Integer.valueOf(jwtTokenUtil.getUserIdFromToken(requestData.getString(Constants.REQUEST_TOKEN_PARAM))));
        favorite.setCreateTime(new Date());
        favorite.setStatus(1);
        favoriteService.insertOrUpdateAllColumn(favorite);
        return ResponseData.success(favorite);
    }

    @RequestMapping(name = "删除收藏", path = "/delete")
    public ResponseData delete(RequestData requestData) {
        Integer userId = Integer.valueOf(jwtTokenUtil.getUserIdFromToken(requestData.getString(Constants.REQUEST_TOKEN_PARAM)));
        Integer poetryId = requestData.getParam().getInteger("poetryId");
        Favorite favorite = favoriteService.selectFavorite(userId, poetryId);
        if (favorite == null) {
            return ResponseData.error(PoetryExceptionEnum.TEMPTY_FAVORITE.getCode(), PoetryExceptionEnum.TEMPTY_FAVORITE.getMessage());
        }
        favorite.setStatus(0);
        favoriteService.update(favorite, null);
        return ResponseData.success();
    }
}
