package com.course_info.udemy_courses_info.controller;

import com.course_info.udemy_courses_info.entity.BunchOfCoursesRequest;
import com.course_info.udemy_courses_info.entity.Course;
import com.course_info.udemy_courses_info.entity.DetailedCourseInfo;
import com.course_info.udemy_courses_info.entity.GeneralDiscountCourseInfo;
import com.course_info.udemy_courses_info.services.CoursesInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roadmaps/courses-info")
@RequiredArgsConstructor
public class CoursesInfoController {

    private final CoursesInfoService coursesInfoService;


    @GetMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public DetailedCourseInfo certainCourseInfo(@PathVariable String courseId) {
        return coursesInfoService.courseInfo(courseId);
    }

    @GetMapping("/{courseId}/discount")
    public GeneralDiscountCourseInfo discountedCoursePrice(@PathVariable String courseId) {
        return coursesInfoService.getCourseDiscountedPrice(courseId);
    }

    @GetMapping("/hot")
    @ResponseStatus(HttpStatus.OK)
    public List<Course> getHotCourses() {
        return coursesInfoService.getHotPropositions();
    }

    @GetMapping("/search/{certainArea}")
    @ResponseStatus(HttpStatus.OK)
    public BunchOfCoursesRequest getCertainDirectionCourses(@PathVariable String certainArea) {
        return coursesInfoService.getCoursesForCertainArea(certainArea);
    }
}
