package com.course_info.udemy_courses_info.dto;

import lombok.Builder;

@Builder
public record DiscountPriceDTO (
    String commonPrice,
    String discountPrice
) {}
