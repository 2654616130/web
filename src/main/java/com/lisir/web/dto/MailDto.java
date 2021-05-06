package com.lisir.web.dto;

/**
 * @Description
 * @Author lxp
 * @Date 2021-03-11 19:18
 * @Version 1.0
 */
public class MailDto {

    private String from;
    private String sendTo;
    private String subject;
    private String text;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
