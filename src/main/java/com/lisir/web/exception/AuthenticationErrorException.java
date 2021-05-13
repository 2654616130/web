package com.lisir.web.exception;

/**
 * @Description
 * @Author lxp
 * @Date 2021-05-08 16:16
 * @Version 1.0
 */
public class AuthenticationErrorException extends RuntimeException {

    public AuthenticationErrorException(String message) {
        super(message);
    }
}
