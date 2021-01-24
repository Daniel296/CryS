package com.wade.crys.alert.email;

import java.text.DecimalFormat;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.wade.crys.alert.model.Alert;
import com.wade.crys.user.model.User;

public class AlertEmailSender {

	private static DecimalFormat df2 = new DecimalFormat("#.##");

	public static void sendEmail(User user, Alert alert) {

		String from = "no-reply@cointrade.com";

		Session session = Session.getInstance(getEmailProperties(), null);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
			message.setSubject("Cointrade alert for " + alert.getCoin().getName() + " cryptocurrency");
			message.setContent(getMessage(user.getFirstName(), alert),"text/html");

			Transport.send(message);

		} catch (MessagingException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private static Properties getEmailProperties() {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", "25");

		return props;
	}

	private static String getMessage(String userName, Alert alert) {

		StringBuilder message = new StringBuilder();

		message.append("<h1>Hello  " + userName + "!</h1>");
		message.append("<br>");
		message.append("<p><img [src]=\"" + alert.getCoin().getLogoURL() + "\" class=\"coin-logo\"> You have a new alarm for "
				+ alert.getCoin().getName() + ". Current price is $" + df2.format(alert.getCoin().getPriceUsd()) + "</p>");
		message.append("<br>");
		message.append("Go to coin <a href=\"localhost:4200/" + alert.getCoin().getId() + "\">page</a>");

		return message.toString();
	}

}
