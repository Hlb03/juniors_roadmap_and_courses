package com.course_info.udemy_courses_info.services;

import com.course_info.udemy_courses_info.entity.BunchOfCoursesRequest;
import com.course_info.udemy_courses_info.entity.Course;
import com.course_info.udemy_courses_info.entity.DetailedCourseInfo;
import com.course_info.udemy_courses_info.entity.GeneralDiscountCourseInfo;
import com.course_info.udemy_courses_info.exceptions.NoSuchCourseException;

import java.util.List;

public interface CoursesInfoService {

    DetailedCourseInfo courseInfo(String courseId);

    GeneralDiscountCourseInfo getCourseDiscountedPrice(String courseIds) throws NoSuchCourseException;

    List<Course> getHotPropositions();

    BunchOfCoursesRequest getCoursesForCertainArea(String areaName);
}
