package com.course_info.udemy_courses_info.entity;

public record Course(
        Long id, String title, String headline,
        String url, String price,
        Double avgRate, String imageUrl_240x135,
        String localeTitle
) {}
