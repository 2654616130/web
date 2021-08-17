package com.lisir.web.util;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Description
 * @Author lxp
 * @Date 2021/8/6 9:39
 * @Version 1.0
 */
public class CommonUtils {

    /**
     * 获取当前登录用户
     * @return
     */
    public String getCurrentUser(){
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
