package com.course_info.udemy_courses_info.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record DetailedCourseDTO(
        Long id,
        String title,
        String price,
        String headline,
        String courseURL,
        String locale,
        String avgRate,
        String enrolledStudents,
        String lecturesAmount,
        String imageUrl_125H,
        String imageUrl_240x135,
        String imageUrl_480x270,
        String description,
        List<String> lecturers) {}
