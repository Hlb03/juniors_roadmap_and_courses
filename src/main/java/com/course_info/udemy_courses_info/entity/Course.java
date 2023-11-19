package com.course_info.udemy_courses_info.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Course {
    private Long id;
    private String title;
    private String price;
    @JsonProperty("avgRate")
    @JsonAlias({"avg_rating"})
    private Double avgRate;
    @JsonProperty("imageUrl_125H")
    @JsonAlias({"image_125_H"})
    private String imageUrl_125H;
    @JsonProperty("imageUrl_240x135")
    @JsonAlias({"image_240x135"})
    private String imageUrl_240x135;
    @JsonProperty("imageUrl_480x270")
    @JsonAlias({"image_480x270"})
    private String imageUrl_480x270;
}
