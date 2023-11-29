package com.course_info.udemy_courses_info.entity.discount_price;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

@Data
public class PricingResult {
    private DiscountAndNormalCoursePrice price;

    @JsonAnySetter
    public void setPrices(String key, DiscountAndNormalCoursePrice details) {
        this.price = details;
    }
}
