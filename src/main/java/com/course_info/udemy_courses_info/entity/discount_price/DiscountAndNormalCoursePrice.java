package com.course_info.udemy_courses_info.entity.discount_price;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DiscountAndNormalCoursePrice(

        @JsonAlias({"discountPrice"})
        NormalPrice normalPrice,
        @JsonAlias({"list_price"})
        DiscountedPrice discountedPrice
) {
}
