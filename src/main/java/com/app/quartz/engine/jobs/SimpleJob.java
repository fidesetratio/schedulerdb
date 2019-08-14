package com.app.quartz.engine.jobs;

import java.util.stream.IntStream;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SimpleJob extends QuartzJobBean {
    
	@Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
	       System.out.println("hehehe begin simple jobs");

		IntStream.range(0, 5).forEach(i -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        });
    }
}
