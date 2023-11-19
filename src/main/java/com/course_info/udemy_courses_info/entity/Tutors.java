package com.course_info.udemy_courses_info.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Tutors(
        @JsonProperty("fullName")
        @JsonAlias({"display_name"})
        String fullName
) {}
