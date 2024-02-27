package com.panpraz.informasisertifikasidanedukasicp;

import javax.jms.TextMessage;

import com.panpraz.informasisertifikasidanedukasicp.constant.SolaceConstant;
//import com.pranpraz.informasisertifikasidanedukasicp.service.CprEndingService;
import com.panpraz.informasisertifikasidanedukasicp.service.ExtendedTenorService;
//import com.pranpraz.informasisertifikasidanedukasicp.service.PostRealizationCprService;
//import com.pranpraz.informasisertifikasidanedukasicp.service.PostRealizationNonCprService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ListenetExtendedTenorEmail {
	private static final Logger logger = LoggerFactory.getLogger(ListenetExtendedTenorEmail.class);

//	@Autowired
//	CprEndingService cprEndingService;
//	@Autowired
//	PostRealizationCprService postRealizationCprService;
//	@Autowired
//	PostRealizationNonCprService postRealizationNonCprService;

	@Autowired
    ExtendedTenorService extendedTenorService;

	public static void main(String[] args) {
		SpringApplication.run(ListenetExtendedTenorEmail.class, args);
	}


	@JmsListener(destination = SolaceConstant.QUEUE_EMAIL_EXTENDED, concurrency = SolaceConstant.THREAD_COUNT)
	public void emailExtendedTenor(Object message) {
		logger.info("Queue "+SolaceConstant.QUEUE_EMAIL_EXTENDED);
		try {
			if(message instanceof TextMessage textMessage) {
				String text = textMessage.getText();
				logger.info("Get New Message From Notification {}",text);
				extendedTenorService.processExtendedTenor(text);
			}
			else {
				throw new IllegalStateException("message: " + message.toString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
