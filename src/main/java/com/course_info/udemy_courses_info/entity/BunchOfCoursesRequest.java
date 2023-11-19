package com.course_info.udemy_courses_info.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BunchOfCoursesRequest (
        @JsonAlias({"count"})
        @JsonProperty("dataAmount")
        String dataAmount,
        @JsonAlias({"results"})
        @JsonProperty("courses")
        List<Course> courses
){}
