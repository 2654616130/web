package com.lisir.web.security;

import com.lisir.web.dao.SysUserDao;
import com.lisir.web.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author lxp
 * @Date 2021-05-06 19:24
 * @Version 1.0
 */
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userDao.queryById(username);
        if (sysUser == null){
            throw new BadCredentialsException("user error");
        }
        return new CustomerUserDetail(username);
    }
}
