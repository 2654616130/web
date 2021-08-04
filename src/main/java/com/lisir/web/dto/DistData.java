package com.lisir.web.dto;

import com.lisir.web.annotation.EnableExport;
import com.lisir.web.annotation.EnableExportField;
import com.lisir.web.annotation.ImportIndex;

/**
 * @Description
 * @Author lxp
 * @Date 2021/8/4 11:46
 * @Version 1.0
 */
@EnableExport(fileName = "测试导出")
public class DistData {

    @EnableExportField(colName = "序号")
    @ImportIndex(index = 0)
    private int index;

    @EnableExportField(colName = "姓名")
    @ImportIndex(index = 1)
    private String userName;

    @EnableExportField(colName = "年龄")
    @ImportIndex(index = 2)
    private int age;

    public DistData() {
    }

    public DistData(int index, String userName, int age) {
        this.index = index;
        this.userName = userName;
        this.age = age;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
