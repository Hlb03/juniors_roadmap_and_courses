package com.course_info.udemy_courses_info.controller;

import com.course_info.udemy_courses_info.entity.Course;
import com.course_info.udemy_courses_info.services.CoursesInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fs2s/roadmaps/courses-info")
@RequiredArgsConstructor
public class CoursesInfoController {

    private final CoursesInfoService coursesInfoService;

    @GetMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public Course certainCourseInfo(@PathVariable String courseId){
        return coursesInfoService.courseInfo(courseId);
    }

    @GetMapping("/hot")
    @ResponseStatus(HttpStatus.OK)
    public String getHotCourses() {
        return coursesInfoService.getHotPropositions();
    }
}
