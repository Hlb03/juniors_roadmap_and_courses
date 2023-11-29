package com.course_info.udemy_courses_info.entity.discount_price;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public record DiscountAndNormalCoursePrice(

        @JsonAlias({"price"})
        @JsonProperty("normalPrice")
        NormalPrice normalPrice,
        @JsonAlias({"list_price"})
        @JsonProperty("discountedPrice")
        DiscountedPrice discountedPrice
) {
}
