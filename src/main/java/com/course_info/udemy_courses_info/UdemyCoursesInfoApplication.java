package com.course_info.udemy_courses_info;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
        info = @Info(
                title = "From start to success (FS2S)",
                description = "This is a backend part of the application. This part is responsible for getting/processing courses from UdemyAPI and return them in another-structured form."
        )
)
public class UdemyCoursesInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UdemyCoursesInfoApplication.class, args);
    }

}
