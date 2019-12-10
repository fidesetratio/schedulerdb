package com.app.quartz.engine.jobs;

import java.util.Date;
import java.util.stream.IntStream;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("-------------------------- Start SimpleJob -------------------------------------");
		System.out.println(new Date().toString());
		
		IntStream.range(0, 5).forEach(i -> {
			try {
				System.out.println("-------------------------- inside SimpleJob -------------------------------------");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		});
		System.out.println("-------------------------- Start SimpleJob -------------------------------------");
	}
}
