package com.course_info.udemy_courses_info.dto;

import com.course_info.udemy_courses_info.entity.detailed_course.Video;
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
        String reviews,
        String courseLevel,
        String lecturesDuration,
        String enrolledStudents,
        String quizzesAmount,
        Boolean hasCertificate,
        String imageUrl_125H,
        String imageUrl_240x135,
        String imageUrl_480x270,
        String description,
        List<Video> videos,
        List<String> requirements,
        List<String> lecturers) {}
