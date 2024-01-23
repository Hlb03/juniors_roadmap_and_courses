package com.course_info.udemy_courses_info.entity.discount_price;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DiscountPrice(
        @JsonAlias({"price_string"})
        String price
) {
}
