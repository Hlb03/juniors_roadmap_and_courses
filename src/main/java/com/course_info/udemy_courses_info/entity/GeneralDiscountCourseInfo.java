package com.course_info.udemy_courses_info.entity;

import com.course_info.udemy_courses_info.entity.discount_price.PricingResult;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralDiscountCourseInfo {
    @JsonAlias({"courses"})
    @JsonProperty("discountInfo")
    private PricingResult discountInfo;
}
