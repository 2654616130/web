package com.lisir.web.service;

import com.alibaba.fastjson.JSON;
import com.lisir.web.dao.SysUserDao;
import com.lisir.web.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author lxp
 * @Date 2021-05-08 10:42
 * @Version 1.0
 */
@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private SysUserDao userDao;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String login(String username, String password){
        SysUser sysUser = userDao.queryById(username);
        if(sysUser == null){
            throw new RuntimeException("请检查用户名是否正确");
        }
        if(!passwordEncoder.matches(password, sysUser.getPwd())){
            throw new RuntimeException("请检查密码是否正确");
        }
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set(token, username, 10, TimeUnit.MINUTES);
        return token;
    }

    public void regeist(SysUser sysUser){
        sysUser.setPwd(passwordEncoder.encode(sysUser.getPwd()));
        userDao.insert(sysUser);
    }
}
