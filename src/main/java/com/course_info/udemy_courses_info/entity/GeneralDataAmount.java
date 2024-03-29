package com.course_info.udemy_courses_info.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public abstract class GeneralDataAmount {
    @JsonAlias({"count"})
    @JsonProperty("dataAmount")
    String dataAmount;
}
