package com.app.quartz.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsigQuartzApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MsigQuartzApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MsigQuartzApplication.class, args);
	};
}
