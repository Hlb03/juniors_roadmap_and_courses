package com.course_info.udemy_courses_info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UdemyCoursesInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemyCoursesInfoApplication.class, args);
	}

}
