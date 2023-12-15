package com.course_info.udemy_courses_info.entity.courses;

import com.course_info.udemy_courses_info.entity.GeneralDataAmount;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BunchOfCourses extends GeneralDataAmount {
        @JsonAlias({"results"})
        @JsonProperty("courses")
        List<Course> courses;
}
