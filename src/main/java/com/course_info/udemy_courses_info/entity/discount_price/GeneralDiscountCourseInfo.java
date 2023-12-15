package com.course_info.udemy_courses_info.entity.discount_price;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralDiscountCourseInfo {
    @JsonAlias({"courses"})
    private PricingResult discountInfo;
}
