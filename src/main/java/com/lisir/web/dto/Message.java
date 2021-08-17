package com.lisir.web.dto;

import java.util.Date;

/**
 * @Description
 * @Author lxp
 * @Date 2021/8/6 9:32
 * @Version 1.0
 */
public class Message {

    private String message;
    private Date date;

    public Message() {
        this.date = new Date();
    }

    public Message(String message) {
        this.message = message;
        this.date = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
