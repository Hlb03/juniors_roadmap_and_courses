package com.course_info.udemy_courses_info.client;

import com.course_info.udemy_courses_info.config.FeignClientConfig;
import com.course_info.udemy_courses_info.entity.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(
        name = "udemy-api-courses-info",
        url = "https://www.udemy.com/api-2.0/courses",
        configuration = FeignClientConfig.class
)
public interface UdemyCoursesClient{

    @GetMapping(value = "/{courseId}", produces = "application/json")
    Course certainCourseInfo(@PathVariable String courseId);

    @GetMapping(value = "?page_size=1", produces = "application/json", headers = "Accept-Language: en-US")
    String getBunchOfCourses(@RequestHeader("Authorization") String authorizationToken);
}
