package com.course_info.udemy_courses_info.services;

import com.course_info.udemy_courses_info.dto.*;
import com.course_info.udemy_courses_info.entity.courses.BunchOfCourses;
import com.course_info.udemy_courses_info.entity.lectures.Lecture;
import com.course_info.udemy_courses_info.exceptions.NoSuchCourseException;

import java.util.List;

public interface CoursesInfoService {

    DetailedCourseDTO courseInfo(String courseId);

    List<ReviewDTO> obtainCourseReviews(String courseId);

    List<Lecture> getCourseLecture(String courseId);

    DiscountPriceDTO getCourseDiscountedPrice(String courseIds) throws NoSuchCourseException;

    List<CourseDTO> getHotPropositions();

    CertainAreaCoursesDTO getCoursesForCertainArea(String areaName, Integer page);
}
