package com.course_info.udemy_courses_info.entity.reviews;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CourseReview (
        @JsonAlias({"rating"})
        String rate,
        String content,
        String created,
        @JsonProperty("user")
        Reviewer reviewer
) {}
