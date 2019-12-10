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
        	System.out.println();
        	System.out.println("------------------------------- start SchedulerStartUpHandler ---------------------------------------------");
            
        	schedulerService.startAllSchedulers();
        	schedulerJobService.resumeAllSchedulers();
        	
        	System.out.println("------------------------------- end SchedulerStartUpHandler ---------------------------------------------");

        } catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println(ex.getMessage());
        }
    }
}