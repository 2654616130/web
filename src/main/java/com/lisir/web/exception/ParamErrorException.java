package com.lisir.web.exception;

/**
 * @Description
 * @Author lxp
 * @Date 2021-05-08 10:48
 * @Version 1.0
 */
public class ParamErrorException extends RuntimeException {

    public ParamErrorException(String message) {
        super(message);
    }
}
