package com.libra.cloud.poetry.service;

import com.libra.cloud.poetry.entity.User;
import com.libra.cloud.poetry.mapper.UserMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
