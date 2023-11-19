package com.course_info.udemy_courses_info.services.implementations;

import com.course_info.udemy_courses_info.client.UdemyCoursesClient;
import com.course_info.udemy_courses_info.entity.*;
import com.course_info.udemy_courses_info.services.CoursesInfoService;
import com.course_info.udemy_courses_info.utils.BearerTokenCreation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CoursesInfoServiceImpl implements CoursesInfoService {

    private final UdemyCoursesClient udemyClient;
    private final BearerTokenCreation tokenCreation;

    private final String limitedFields = "id,title,avg_rating,price,image_125_H,image_240x135,image_480x270";
    private final String allCoursesFields = "id,title,avg_rating,price,image_125_H,image_240x135,image_480x270,headline,url,locale,visible_instructors,num_subscribers";

    @Override
    public DetailedCourseInfo courseInfo(String courseId) {
        log.info("Gaining info about course with id " + courseId);
        return udemyClient.getCertainCourseInfo(courseId, allCoursesFields);
    }

    //TODO: finish method implementation -> should return discounted price for certain course
    @Override
    public GeneralDiscountCourseInfo getCourseDiscountedPrice(String courseIds) {
        log.info("Get discounted course (ids: {}) price ",courseIds);
        return udemyClient.getCourseDiscountPrice(courseIds);
    }

    @Override
    public List<Course> getHotPropositions() {
        log.info("Getting most popular courses");
        return udemyClient
                .getBunchOfCourses(tokenCreation.getEncodedToken(), limitedFields)
                .courses();
    }

    @Override
    public BunchOfCoursesRequest getCoursesForCertainArea(String areaName) {
        log.info("Get courses dedicated to {}", areaName);
        return udemyClient.getSpecifiedAreaCourses(areaName, tokenCreation.getEncodedToken(), limitedFields);
    }
}
