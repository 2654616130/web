package com.lisir.web.rest;

import com.lisir.web.service.QRCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author lxp
 * @Date 2021-05-06 10:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/qr")
public class QrCodeImageController {

    @Resource
    QRCodeService qrCodeService;

    @GetMapping("/image")
    public void createQrCodeImage(String content,
                                  @RequestParam(required = false, defaultValue = "300") int width,
                                  @RequestParam(required = false, defaultValue = "300") int height) {
        qrCodeService.genQRCodeImage(content, width, height);
    }
}
