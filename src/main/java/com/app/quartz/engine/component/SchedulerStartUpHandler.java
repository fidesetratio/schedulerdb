package com.app.quartz.engine.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.app.quartz.engine.service.SchedulerJobService;
import com.app.quartz.engine.service.SchedulerService;

import lombok.extern.slf4j.Slf4j;

/**
 * Scheduler Start Up
 * @author Patar.Tambunan
 *
 */


@Slf4j
@Component
public class SchedulerStartUpHandler implements ApplicationRunner {

    @Autowired
    private SchedulerService schedulerService;
    
    @Autowired
    private SchedulerJobService schedulerJobService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            schedulerService.startAllSchedulers();
            schedulerJobService.resumeAllSchedulers();
        } catch (Exception ex) {
        }
    }
}