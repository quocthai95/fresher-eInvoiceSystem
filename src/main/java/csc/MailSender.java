package csc;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class MailSender {
	final String senderEmailID = "nguyenvanantest123@gmail.com";
	final String senderPassword = "Nguyenvanantest123!@#";
	final String emailSMTPserver = "smtp.gmail.com";
	final String emailServerPort = "465";
	String receiverEmailID = null;
	static String emailSubject = "Test Mail";
	static String emailBody = ":)";

	public MailSender(String receiverEmailID, String emailSubject, String emailBody) {
		this.receiverEmailID = receiverEmailID;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
		Properties props = new Properties();
		// props.put("mail.smtp.user", senderEmailID);
		// props.put("mail.smtp.host", emailSMTPserver);
		// props.put("mail.smtp.port", emailServerPort);
		// props.put("mail.smtp.starttls.enable", "true");
		// props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.socketFactory.port", emailServerPort);
		// props.put("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// props.put("mail.smtp.socketFactory.fallback", "false");
		// props.setProperty("mail.smtp.quitwait", "false");

		props.put("mail.smtp.user", senderEmailID);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", emailServerPort);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		SecurityManager security = System.getSecurityManager();
		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(emailBody);
			msg.setSubject(emailSubject);
			msg.setFrom(new InternetAddress(senderEmailID));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmailID));
			Transport.send(msg);
			System.out.println("Message send Successfully:)");
		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}

	public class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(senderEmailID, senderPassword);
		}
	}

	public static void main(String[] args) {
		System.out.println("send it");
		// MailSender mailSender = new MailSender("hailt09111994@gmail.com",
		// emailSubject, emailBody);

		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", true); // added this line
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.user", "nguyenvanantest123@gmail.com");
		props.put("mail.smtp.password", "Nguyenvanantest123!@#");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", true);

		Session session = Session.getInstance(props, null);
		MimeMessage message = new MimeMessage(session);

		System.out.println("Port: " + session.getProperty("mail.smtp.port"));

		// Create the email addresses involved
		try {
			InternetAddress from = new InternetAddress("nguyenvanantest123@gmail.com");
			message.setSubject("Yes we can");
			message.setFrom(from);
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("hailt09111994@gmail.com"));

			// Create a multi-part to combine the parts
			Multipart multipart = new MimeMultipart("alternative");

			// Create your text message part
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("some text to send");

			// Add the text part to the multipart
			multipart.addBodyPart(messageBodyPart);

			// Create the html part
			messageBodyPart = new MimeBodyPart();
			String htmlMessage = "Our html text";
			messageBodyPart.setContent(htmlMessage, "text/html");

			// Add html part to multi part
			multipart.addBodyPart(messageBodyPart);

			// Associate multi-part with message
			message.setContent(multipart);

			// Send message
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", "nguyenvanantest123@gmail.com", "Nguyenvanantest123!@#");
			System.out.println("Transport: " + transport.toString());
			transport.sendMessage(message, message.getAllRecipients());

		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}