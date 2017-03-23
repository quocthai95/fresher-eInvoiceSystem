package csc.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import csc.config.SendMail;
import csc.models.Parameter;
import csc.service.ParameterService;

@Component
public class ScheduledTasks {
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	private static Parameter para;

	private static String CRON = "0 10 23 22 * ?";
	@Autowired
	ParameterService parameterService;

	@Scheduled(fixedRate = 20000)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		para = parameterService.findById(1);
		//Check account email
		log.info("username= " + para.getEmail() + "pwd= " + para.getPwdEmail());
		
	}

	@Scheduled(cron = "0 10 23 22 * ?") // "Date 22
	public void sendMailByTime() {
		String[] to = null;
		
		SendMail.sendMailSMTP(para.getEmail(), para.getPwdEmail(), to);
	}
}
