package com.lisir.web.rest;

import com.lisir.web.dto.LoginUser;
import com.lisir.web.entity.SysUser;
import com.lisir.web.exception.ParamErrorException;
import com.lisir.web.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author lxp
 * @Date 2021-05-08 10:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginUser user){
        if(StringUtils.isEmpty(user.getUsername())){
            throw new ParamErrorException("用户名不能为空");
        }
        if(StringUtils.isEmpty(user.getPassword())){
            throw new ParamErrorException("密码不能为空");
        }
        return userService.login(user.getUsername(), user.getPassword());
    }

    @PutMapping("/sign/up")
    public String signIn(@RequestBody SysUser user){
        userService.regeist(user);
        return "恭喜你, 注册成功, 请前往登陆页面登陆";
    }
}
