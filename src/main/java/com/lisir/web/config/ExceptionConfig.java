package com.lisir.web.config;

import com.lisir.web.exception.AuthenticationErrorException;
import com.lisir.web.exception.ParamErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;

/**
 * @Description
 * @Author lxp
 * @Date 2021-05-08 10:44
 * @Version 1.0
 */
@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(ParamErrorException.class)
    public ResponseEntity<Object> handleParamErrorException(Exception e, WebRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, Object>(){{
            put("type", "请求参数异常");
            put("time", new Date());
            put("path", ((ServletWebRequest)request).getRequest().getRequestURI());
            put("message", e.getMessage());
        }});
    }

    @ExceptionHandler(AuthenticationErrorException.class)
    public ResponseEntity<Object> handleAuthenticationErrorException(Exception e, WebRequest request){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new HashMap<String, Object>(){{
            put("type", "用户认证异常");
            put("time", new Date());
            put("path", ((ServletWebRequest)request).getRequest().getRequestURI());
            put("message", e.getMessage());
        }});
    }
}
