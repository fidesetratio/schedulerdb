package com.helix.quartz.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.helix.quartz.demo.service.SchedulerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SchedulerStartUpHandler implements ApplicationRunner {

    @Autowired
    private SchedulerService schedulerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	
    	System.out.println("start runn");
        try {
            schedulerService.startAllSchedulers();
        } catch (Exception ex) {
        }
    }
}