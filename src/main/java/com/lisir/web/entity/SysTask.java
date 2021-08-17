package com.lisir.web.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysTask)实体类
 *
 * @author makejava
 * @since 2021-08-05 17:09:07
 */
public class SysTask implements Serializable {
    private static final long serialVersionUID = -98488826405766828L;
    
    private Long id;
    /**
    * 任务名
    */
    private String jobName;
    /**
    * 任务描述
    */
    private String description;
    /**
    * cron表达式
    */
    private String cronExpression;
    /**
    * 任务执行时调用哪个类的方法 包名+类名
    */
    private String beanClass;
    /**
    * 任务状态
    */
    private String jobStatus;
    /**
    * 任务分组
    */
    private String jobGroup;
    /**
    * 创建者
    */
    private String createUser;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新者
    */
    private String updateUser;
    /**
    * 更新时间
    */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}