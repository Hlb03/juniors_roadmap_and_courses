package com.course_info.udemy_courses_info.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;


@Data
@EqualsAndHashCode(callSuper = true)
public class DetailedCourseInfo extends Course {
    private String headline;
    private String url;
    private String localeTitle;
    @JsonAlias({"num_subscribers"})
    @JsonProperty("enrolledStudents")
    private String enrolledStudents;

    @JsonAlias({"visible_instructors"})
    @JsonProperty("tutors")
    private List<Tutors> tutors;

    @JsonProperty("locale")
    private void mapNestedLocale(Map<String, Object> locale) {
        this.localeTitle = (String) locale.get("english_title");
    }
}
