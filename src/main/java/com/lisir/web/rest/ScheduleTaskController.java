package com.lisir.web.rest;

import com.lisir.web.dto.Message;
import com.lisir.web.entity.SysTask;
import com.lisir.web.exception.ParamErrorException;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronExpression;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author lxp
 * @Date 2021/8/6 9:29
 * @Version 1.0
 */
@RestController
@RequestMapping("/schedule/task")
public class ScheduleTaskController {

    @PutMapping("/add")
    public Message addTask(@RequestBody SysTask task){
        if(StringUtils.isEmpty(task.getBeanClass())){
            throw new ParamErrorException("任务执行对象不能为空");
        }
        if(StringUtils.isEmpty(task.getCronExpression())){
            throw new ParamErrorException("");
        }
        return new Message("添加定时任务成功");
    }
}
