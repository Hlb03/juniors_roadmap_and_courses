package com.course_info.udemy_courses_info.client;

import com.course_info.udemy_courses_info.entity.courses.BunchOfCourses;
import com.course_info.udemy_courses_info.entity.lectures.BunchOfCourseLectures;
import com.course_info.udemy_courses_info.entity.reviews.BunchOfReviews;
import com.course_info.udemy_courses_info.entity.detailed_course.DetailedCourseInfo;
import com.course_info.udemy_courses_info.entity.discount_price.GeneralDiscountCourseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(
        name = "${feign.client.name}",
        url = "${feign.client.udemy.api.url}"
)
public interface UdemyCoursesClient {

    @GetMapping(value = "courses/{courseId}", produces = "application/json")
    DetailedCourseInfo getCertainCourseInfo(@PathVariable(name = "courseId") String courseId, @RequestParam("fields[course]") String requiredFields);

    //TODO: inspect page size could be less
    @GetMapping(value = "courses/{courseId}/reviews?page_size=40&page=1", produces = "application/json")
    BunchOfReviews getCourseReviews(@PathVariable(name = "courseId") String courseId);

    // TODO: check after full proj connection whether page_size could be decreased to 10
    @GetMapping("courses/{courseId}/public-curriculum-items?page_size=12")
    BunchOfCourseLectures getCourseLectures(@PathVariable (name = "courseId") String courseId, @RequestParam("fields[lecture]") String requiredFields);

    @GetMapping(value = "pricing")
    GeneralDiscountCourseInfo getCourseDiscountPrice(@RequestParam("course_ids") String courseIds);

    // TODO: add "ordering" param with 'relevance' value to the request
    @GetMapping(value = "courses?page_size=10", produces = "application/json")
    BunchOfCourses getBunchOfCourses(@RequestHeader("Authorization") String authorizationToken, @RequestParam("fields[course]") String requiredFields);

    //TODO: figure out data amount for one page
    @GetMapping(value = "courses?search={areaName}", produces = "application/json")
    BunchOfCourses getSpecifiedAreaCourses(
            @PathVariable(name = "areaName") String areaName,
            @RequestHeader("Authorization") String authorizationToken,
            @RequestParam("fields[course]") String requiredFields
    );
}
