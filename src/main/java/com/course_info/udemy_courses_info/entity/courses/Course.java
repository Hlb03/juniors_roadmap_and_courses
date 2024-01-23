package com.course_info.udemy_courses_info.entity.courses;

import com.course_info.udemy_courses_info.entity.discount_price.Discount;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class Course {
    private Long id;
    private String title;
    private String price;
    @JsonAlias({"discount"})
    private Discount discount;
    @JsonAlias({"instructional_level"})
    private String instructionalLevel;
    @JsonAlias({"avg_rating"})
    private String avgRate;
    @JsonAlias({"image_125_H"})
    private String imageUrl_125H;
    @JsonAlias({"image_240x135"})
    private String imageUrl_240x135;
    @JsonAlias({"image_480x270"})
    private String imageUrl_480x270;
    @JsonAlias({"caption_languages"})
    private List<String> courseLanguages;
}
