package com.course_info.udemy_courses_info.services;

import com.course_info.udemy_courses_info.entity.Course;

public interface CoursesInfoService {

    Course courseInfo(String courseId);

    String getHotPropositions();
}
