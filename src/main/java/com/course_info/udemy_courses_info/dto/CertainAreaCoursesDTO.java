package com.course_info.udemy_courses_info.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CertainAreaCoursesDTO (
    String dataAmount,
    List<CourseDTO> courses
){}
