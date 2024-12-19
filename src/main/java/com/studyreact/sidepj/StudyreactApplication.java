package com.studyreact.sidepj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.studyreact.sidepj", "configs"})
//SpringBoot 애플리케이션은 기본적으로 com.studyreact.sidepj패키지만 스캔한다.
//때문에 configs.security를 스캔하도록 설정해줘야 한다.
public class StudyreactApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyreactApplication.class, args);
	}

}
