package com.piaandersin.pagecheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PagecheckApplication {

	public static void main(String[] args) {
                SpringApplication.run(PagecheckApplication.class, args);
	}

}
