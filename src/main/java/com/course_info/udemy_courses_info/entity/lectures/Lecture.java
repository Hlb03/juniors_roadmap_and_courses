package com.course_info.udemy_courses_info.entity.lectures;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = {"type"}, allowSetters = true)
public record Lecture(

        @JsonAlias({"_class"})
        String type,
        String title,
        String created,
        String description,
        @JsonAlias({"content_summary"})
        @JsonProperty("duration")
        String duration
) {}
