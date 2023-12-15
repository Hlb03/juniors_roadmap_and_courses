package com.course_info.udemy_courses_info.entity.lectures;

import com.course_info.udemy_courses_info.entity.GeneralDataAmount;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
public class BunchOfCourseLectures extends GeneralDataAmount {
    @JsonAlias({"results"})
    @JsonProperty("lectures")
    List<Lecture> lectures;
}
