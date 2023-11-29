package com.course_info.udemy_courses_info.entity.discount_price;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractDiscountPrice {

    private String amount;
    private String currency;
    private String price_string;
}
