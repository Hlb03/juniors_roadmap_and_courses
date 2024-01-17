package com.course_info.udemy_courses_info.entity.detailed_course;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Optional;

public record PromoAssets(
        @JsonAlias({"download_urls"})
        PromoVideo promoVideo
) {}
