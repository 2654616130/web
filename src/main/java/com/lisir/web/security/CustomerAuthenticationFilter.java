package com.lisir.web.security;

import com.lisir.web.exception.AuthenticationErrorException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @Author lxp
 * @Date 2021-05-06 18:46
 * @Version 1.0
 */
public class CustomerAuthenticationFilter extends BasicAuthenticationFilter {

    private List<String> ignoreUrls;

    private final PathMatcher pathMatcher = new AntPathMatcher();

    public CustomerAuthenticationFilter(AuthenticationManager authenticationManager, List<String> ignoreUrls) {
        super(authenticationManager);
        this.ignoreUrls = ignoreUrls;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        if(ignoreUrls != null && !ignoreUrls.isEmpty()){
            for(String url : ignoreUrls){
                if(pathMatcher.match(url, requestURI)){
                    chain.doFilter(request, response);
                    return;
                }
            }
        }
        String token = request.getHeader("Authorization");
        if(StringUtils.isEmpty(token)){
            throw new AuthenticationErrorException("permission denied !!!");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(null, token);
        Authentication authenticate = getAuthenticationManager().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        chain.doFilter(request, response);
    }

}
