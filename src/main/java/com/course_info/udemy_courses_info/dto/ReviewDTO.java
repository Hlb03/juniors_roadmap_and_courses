package com.course_info.udemy_courses_info.dto;

import lombok.Builder;

@Builder
public record ReviewDTO (
    String rate,
    String content,
    String createdAt,
    String author
){}
