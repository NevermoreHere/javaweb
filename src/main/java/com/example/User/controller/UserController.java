package com.example.User.controller;


import com.example.User.entity.User;
import com.example.User.service.IUserService;
import com.example.Utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-07-16
 */
@RestController
@RequestMapping("/User/user")
@Api(value = "用户信息管理")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public CommonResult login(HttpServletRequest request, @RequestBody @ApiParam(value = "user login", required = true) User user) {
        String username = user.getUserName();//用户名
        String password = user.getPassword();//密码
        //查询用户名和密码是否匹配,是否存在
        User res_user = userService.verifyPassword(username, password);
        if (null != res_user) {
            HttpSession session = request.getSession();
            session.setAttribute(session.getId(),user);

            return new CommonResult(res_user);
        }
        CommonResult cr = new CommonResult<User>();
        cr.setErrorCode("401");
        cr.setErrorMsg("账户不存在或者账户密码错误");
        return cr;
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public CommonResult register(@RequestBody @ApiParam(value = "user register", required = true) User user) {
        //查询用户名和密码是否匹配,是否存在
        boolean res = userService.createUser(user);
        if (res) {
            return new CommonResult();
        }
        CommonResult cr = new CommonResult<User>();
        cr.setErrorCode("101");
        cr.setErrorMsg("账户已存在");
        return cr;
    }
}
