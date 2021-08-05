package com.example.User.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.User.entity.User;
import com.example.User.mapper.UserMapper;
import com.example.User.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.Utils.EmailUtils;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-07-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    public User verifyPassword(String username, String password){
        User user = new LambdaQueryChainWrapper<>(userMapper).eq(User::getUserName, username).one();
        if (user == null){
            return null;
        }
        if (DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)).equals(user.getPassword())){
            return user;
        }
        return null;
    }

    public boolean createUser(User user){
        String md5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(md5);
        try {
            userMapper.insert(user);

        }catch (
                Exception e
        )
        {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
