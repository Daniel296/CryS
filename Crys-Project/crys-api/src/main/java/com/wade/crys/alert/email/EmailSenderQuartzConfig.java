package com.wade.crys.alert.email;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wade.crys.alert.interfaces.AlertService;
import com.wade.crys.user.interfaces.UserService;

@Configuration
public class EmailSenderQuartzConfig {

	@Autowired
	private AlertService alertService;

	@Autowired
	private UserService userService;

	@Bean
	public Scheduler alertEmailScheduler() throws SchedulerException {

		Scheduler scheduler =  new StdSchedulerFactory().getScheduler();
		scheduler.scheduleJob(jobDetail(), trigger());
		scheduler.getContext().put("alertService", alertService);
		scheduler.getContext().put("userService", userService);
		scheduler.start();

		return scheduler;
	}

	private JobDetail jobDetail() {

		return JobBuilder.newJob().ofType(AlertEmailSenderJob.class)
				.withIdentity("Quartz_Alert_Email_Sender_Job")
				.withDescription("Invoke Alert Email Sender Job service...")
				.build();
	}

	private Trigger trigger() {

		return TriggerBuilder.newTrigger()
				.withIdentity("Quartz_Alert_Email_Sender_Job")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(60)
						.repeatForever())
				.build();
	}
}
