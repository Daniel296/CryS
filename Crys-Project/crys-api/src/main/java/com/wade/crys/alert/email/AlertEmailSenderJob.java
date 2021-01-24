package com.wade.crys.alert.email;

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

import com.wade.crys.alert.interfaces.AlertService;
import com.wade.crys.alert.model.Alert;
import com.wade.crys.user.interfaces.UserService;
import com.wade.crys.user.model.User;
import com.wade.crys.utils.AlertHelper;

@Component
@DisallowConcurrentExecution
public class AlertEmailSenderJob implements Job {

	private AlertService alertService;

	private UserService userService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		try {

			alertService = (AlertService)  context.getScheduler().getContext().get("alertService");
			userService = (UserService)  context.getScheduler().getContext().get("userService");
		} catch (SchedulerException e) {

			e.printStackTrace();
		}

		List<User> users = userService.getUsersWithEmailNotification();

		for(User user : users) {

			alertService.getUserAlertsThatShouldBeTriggered(user.getUuid())
					.forEach(alert -> AlertEmailSender.sendEmail(user, alert));
		}

	}
}
