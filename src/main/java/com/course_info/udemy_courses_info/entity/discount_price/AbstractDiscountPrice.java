package com.course_info.udemy_courses_info.entity.discount_price;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractDiscountPrice {
    @JsonAlias({"price_string"})
    private String price;
}
