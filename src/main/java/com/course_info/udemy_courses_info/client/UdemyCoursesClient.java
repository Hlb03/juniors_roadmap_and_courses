package com.course_info.udemy_courses_info.client;

import com.course_info.udemy_courses_info.config.FeignClientConfig;
import com.course_info.udemy_courses_info.entity.BunchOfCoursesRequest;
import com.course_info.udemy_courses_info.entity.GeneralDiscountCourseInfo;
import com.course_info.udemy_courses_info.entity.PricingResult;
import com.course_info.udemy_courses_info.entity.DetailedCourseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(
        name = "${feign.client.name}",
        url = "${feign.client.udemy.api.url}",
        configuration = FeignClientConfig.class
)
public interface UdemyCoursesClient{

    @GetMapping(value = "courses/{courseId}", produces = "application/json")
    DetailedCourseInfo getCertainCourseInfo(@PathVariable(name = "courseId") String courseId, @RequestParam("fields[course]") String requiredFields);

    // TODO: parse JSON response properly
    @GetMapping(value = "pricing")
    GeneralDiscountCourseInfo getCourseDiscountPrice(@RequestParam("course_ids") String courseIds);

    @GetMapping(value = "courses?page_size=10", produces = "application/json")
    BunchOfCoursesRequest getBunchOfCourses(@RequestHeader("Authorization") String authorizationToken, @RequestParam("fields[course]") String requiredFields);

    //TODO: figure out data amount for one page
    @GetMapping(value = "courses?search={areaName}", produces = "application/json")
    BunchOfCoursesRequest getSpecifiedAreaCourses(
            @PathVariable(name = "areaName") String areaName,
            @RequestHeader("Authorization") String authorizationToken,
            @RequestParam("fields[course]") String requiredFields
            );
}
