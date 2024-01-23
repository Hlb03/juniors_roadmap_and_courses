package com.course_info.udemy_courses_info.dto;
import lombok.Builder;

import java.util.List;

@Builder
public record CourseDTO(
        Long id,
        String title,
        String courseLevel,
        String price,
        String discount,
        String avgRate,
        String imageUrl_125H,
        String imageUrl_240x135,
        String imageUrl_480x270,
        List<String> courseLanguages
) {}
