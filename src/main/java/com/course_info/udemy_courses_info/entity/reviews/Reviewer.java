package com.course_info.udemy_courses_info.entity.reviews;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Reviewer (
        @JsonAlias({"display_name"})
        String displayName
){}
