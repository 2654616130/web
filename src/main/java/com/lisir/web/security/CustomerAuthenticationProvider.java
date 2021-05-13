package com.lisir.web.security;

import com.lisir.web.exception.AuthenticationErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author lxp
 * @Date 2021-05-06 19:20
 * @Version 1.0
 */
@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomerUserDetailsService userDetailsService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();
        String username = (String) redisTemplate.opsForValue().get(token);
        if(StringUtils.isEmpty(username)){
            throw new AuthenticationErrorException("token expire");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), token, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
