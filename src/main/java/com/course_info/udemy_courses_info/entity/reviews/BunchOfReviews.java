package com.course_info.udemy_courses_info.entity.reviews;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// TODO: inspect whether we want to add pagination on course reviews (yes - extend GeneralDataAmount)
public record BunchOfReviews (
    @JsonAlias({"results"})
    @JsonProperty("courses")
    List<CourseReview> courses
){}
