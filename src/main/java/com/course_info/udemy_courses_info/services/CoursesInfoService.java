package com.course_info.udemy_courses_info.services;

import com.course_info.udemy_courses_info.entity.*;

import java.util.List;

public interface CoursesInfoService {

    DetailedCourseInfo courseInfo(String courseId);

    GeneralDiscountCourseInfo getCourseDiscountedPrice(String courseIds);

    List<Course> getHotPropositions();

    BunchOfCoursesRequest getCoursesForCertainArea(String areaName);
}
