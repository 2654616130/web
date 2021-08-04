package com.lisir.web.rest;

import com.alibaba.fastjson.JSON;
import com.lisir.web.dto.DistData;
import com.lisir.web.util.ExcelUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author lxp
 * @Date 2021-02-07 11:37
 * @Version 1.0
 */
@RestController
public class TestContrller {


//    @PreAuthorize("hasAuthority('system_admin')")
    @GetMapping("/test")
    public String test(){
        return "hello, docker";
    }

    @PostMapping("/test/import")
    public String testImport(MultipartFile file) throws IOException {
        return JSON.toJSONString(ExcelUtils.parseExcelToEntityList(file.getInputStream(), DistData.class));
    }

    @GetMapping("/test/export")
    public void testExport(HttpServletResponse response) {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            DistData distData = new DistData(i, "TOM-" + i, i);
            list.add(distData);
        }
        ExcelUtils.export(list, DistData.class, response);
    }
}
