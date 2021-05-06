package com.lisir.web.service;

import com.lisir.web.dto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author lxp
 * @Date 2021-02-24 17:13
 * @Version 1.0
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(MailDto mailDto){
        mailSender.send(mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setFrom(mailDto.getFrom());
            message.setTo(mailDto.getSendTo());
            message.setSubject(mailDto.getSubject());
            message.setText(mailDto.getText());
        });
    }
}
