package com.app.quartz.engine.listeners;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

public class CustomListeners implements TriggerListener {

	private String name;

	public CustomListeners(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		System.out.println();
		System.out.println("1 ==> triggerFired");
	}

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		System.out.println("2 ==> vetoJobExecution");
		return false;
	}

	@Override
	public void triggerMisfired(Trigger trigger) {
		System.out.println("3 ==> triggerMisfired");
	};

	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			CompletedExecutionInstruction triggerInstructionCode) {
		System.out.println("4 ==> triggerComplete");
		System.out.println();

	}

}
