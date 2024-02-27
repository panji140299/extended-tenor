package com.panpraz.eventlunas;

import com.panpraz.eventlunas.model.extendedtenor.ExtendedTenor;
import com.panpraz.eventlunas.model.extendedtenor.ExtendedTenorRepository;
import com.panpraz.eventlunas.service.ExtendedTenorService;
import com.panpraz.eventlunas.service.PublisherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class EventExtendedTenor {

	@Autowired
	private ExtendedTenorRepository extendedTenorRepository;

	@Autowired
	ExtendedTenorService extendedTenorService;

	@Autowired
	PublisherService publisherService;

	public static void main(String[] args) {
		SpringApplication.run(EventExtendedTenor.class, args);
	}

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));
	}


	@Scheduled(cron = "${extended.cron.expression}")
	public void getDataExtendedTenor() {
		long now = System.currentTimeMillis() / 1000;
		log.info("Scheduler Is Running - {}", now);
		Integer daysAfter = 5;
		LocalDate localDate = LocalDate.now().minusDays(daysAfter);

		try {
			log.info("Starting to Extended Tenor customer that has +5  {}", localDate);
			List<ExtendedTenor> extendedTenorList = extendedTenorService.getExtendedTenorEmail();

			for (ExtendedTenor extendedTenor : extendedTenorList) {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String payload = ow.writeValueAsString(extendedTenor);
				log.info("Found Extended Tenor customer at {} day. The customer: {}", localDate, payload);
				publisherService.sendingMessagetoSolace(payload,"T/PANPRAZ/JOBS/EXTENDEDTENOR/EMAIL");
			}
		} catch (Exception e) {
			log.error("Something Went Terribly Wrong. Di Cek ya", e);
		}
	}

}
