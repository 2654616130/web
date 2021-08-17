package com.lisir.web.config;

import com.lisir.web.service.ScheduleTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description 定时任务服务类
 * @Author lxp
 * @Date 2021/8/5 14:47
 * @Version 1.0
 */
@Component
public class ScheduleJobInitListener implements CommandLineRunner {

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    @Override
    public void run(String... args) throws Exception {
        scheduleTaskService.init();
    }
}
