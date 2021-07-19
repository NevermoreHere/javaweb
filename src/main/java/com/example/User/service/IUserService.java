package com.example.User.service;

import com.example.User.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2021-07-16
 */
public interface IUserService extends IService<User> {
    User verifyPassword(String username, String password);

    boolean createUser(User user);
}
