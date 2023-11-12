package com.course_info.udemy_courses_info.services.implementations;

import com.course_info.udemy_courses_info.client.UdemyCoursesClient;
import com.course_info.udemy_courses_info.entity.Course;
import com.course_info.udemy_courses_info.services.CoursesInfoService;
import com.course_info.udemy_courses_info.utils.BearerTokenCreation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class CoursesInfoServiceImpl implements CoursesInfoService {

    private final UdemyCoursesClient udemyClient;
    private final BearerTokenCreation tokenCreation;

    @Override
    public Course courseInfo(String courseId) {
        log.info("Gaining info about course with id " + courseId);
        return udemyClient.certainCourseInfo(courseId);
    }

    @Override
    public String getHotPropositions() {
        log.info("Getting most popular courses");
        return udemyClient.getBunchOfCourses(tokenCreation.getEncodedToken());
    }
}
