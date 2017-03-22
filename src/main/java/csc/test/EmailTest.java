package csc.test;

import static org.junit.Assert.fail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class EmailTest {

	/**
	 * test function send mail by stmp
	 */
	@Test
	public void sendMailSMTP() {
		final String username = "nguyenvanantest123@gmail.com";
		final String password = "Nguyenvanantest123!@#";

		String[] to = { "hailt09111994@gmail.com",
				// "quoctc11@gmail.com",
				// "letuananhuit@gmail.com",
				// "ngocnguyen552015@gmail.com",
				// "nqthai95@gmail.com"
		};
		String from = "nguyenvanantest123@gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			session.setDebug(true);
			Message message = new MimeMessage(session);

			InternetAddress[] addressTo = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				addressTo[i] = new InternetAddress(to[i]);
			}

			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.BCC, addressTo);
			message.setSubject("Testing Subject");
			message.setText("Dear my team," + "\n\n Test function send mail." + "\n\n No spam to my email, please!");
			// send mail
			Transport.send(message);

			System.out.println("sendMailSMTP Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
