package com.course_info.udemy_courses_info.entity.detailed_course;

import com.course_info.udemy_courses_info.entity.courses.Course;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Data
@EqualsAndHashCode(callSuper = true)
public class DetailedCourseInfo extends Course {
    private String headline;
    private String url;
    private String localeTitle;
    @JsonAlias({"num_subscribers"})
    private String enrolledStudents;

    @JsonAlias({"num_reviews"})
    private String reviewsNumber;
    @JsonAlias({"num_quizzes"})
    private String quizzesAmount;
    private String description;
    @JsonAlias({"requirements_data"})
    private Requirements requirements;
    @JsonAlias({"instructional_level"})
    private String instructionalLevel;

    @JsonAlias({"has_certificate"})
    private Boolean certificatePresence;
    @JsonAlias({"content_info_short"})
    private String lecturesDuration;
    @JsonAlias({"visible_instructors"})
    private List<Tutors> tutors;
    @JsonAlias({"promo_asset"})
    private PromoAssets promo;

    @JsonProperty("locale")
    private void mapNestedLocale(Map<String, Object> locale) {
        this.localeTitle = (String) locale.get("english_title");
    }
}
