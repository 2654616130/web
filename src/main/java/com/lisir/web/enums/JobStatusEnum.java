package com.lisir.web.enums;

/**
 * @Description
 * @Author lxp
 * @Date 2021/8/5 17:33
 * @Version 1.0
 */
public enum  JobStatusEnum {

    STOP("0", "停止"),
    RUNNING("1", "运行");

    private String code;
    private String value;

    JobStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
