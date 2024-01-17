package com.course_info.udemy_courses_info.services.implementations;

import com.course_info.udemy_courses_info.client.UdemyCoursesClient;
import com.course_info.udemy_courses_info.dto.DetailedCourseDTO;
import com.course_info.udemy_courses_info.dto.DiscountPriceDTO;
import com.course_info.udemy_courses_info.dto.ReviewDTO;
import com.course_info.udemy_courses_info.entity.courses.BunchOfCourses;
import com.course_info.udemy_courses_info.entity.courses.Course;
import com.course_info.udemy_courses_info.entity.detailed_course.DetailedCourseInfo;
import com.course_info.udemy_courses_info.entity.detailed_course.Tutors;
import com.course_info.udemy_courses_info.entity.discount_price.GeneralDiscountCourseInfo;
import com.course_info.udemy_courses_info.entity.lectures.Lecture;
import com.course_info.udemy_courses_info.entity.reviews.CourseReview;
import com.course_info.udemy_courses_info.exceptions.NoSuchCourseException;
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

    private final String LIMITED_COURSE_FIELDS = "id,title,avg_rating,price,image_125_H,image_240x135,image_480x270,caption_languages";
    private final String REQUIRED_COURSE_FIELDS = "id,title,avg_rating,num_reviews,price,image_125_H,image_240x135,image_480x270,headline,description,url,locale,visible_instructors,num_subscribers,num_quizzes,instructional_level,content_info_short,has_certificate,requirements_data,promo_asset";
    private final String REQUIRED_FIELDS_FOR_LECTURE = "_class,title,created,description,content_summary";

    // TODO: course which r not available now (e.g. id 533680) r throwing 403 status and 500 as a result
    // TODO: videos could be null. Inspect how to omit NPE
    @Override
    public DetailedCourseDTO courseInfo(String courseId) {
        log.info("Gaining info about course with id " + courseId);
        DetailedCourseInfo courseInfo = udemyClient.getCertainCourseInfo(courseId, REQUIRED_COURSE_FIELDS);
        log.info("Videos {}", courseInfo.getPromo());
        return DetailedCourseDTO.builder()
                .id(courseInfo.getId())
                .title(courseInfo.getTitle())
                .price(courseInfo.getPrice())
                .headline(courseInfo.getHeadline())
                .description(courseInfo.getDescription())
                .avgRate(courseInfo.getAvgRate())
                .reviews(courseInfo.getReviewsNumber())
                .hasCertificate(courseInfo.getCertificatePresence())
                .courseLevel(courseInfo.getInstructionalLevel())
                .lecturesDuration(courseInfo.getLecturesDuration())
                .imageUrl_125H(courseInfo.getImageUrl_125H())
                .imageUrl_240x135(courseInfo.getImageUrl_240x135())
                .imageUrl_480x270(courseInfo.getImageUrl_480x270())
                .courseURL(courseInfo.getUrl())
                .locale(courseInfo.getLocaleTitle())
                .enrolledStudents(courseInfo.getEnrolledStudents())
                .quizzesAmount(courseInfo.getQuizzesAmount())
                .videos((courseInfo.getPromo() != null) ? courseInfo.getPromo().promoVideo().videos() : List.of())
                .requirements(courseInfo.getRequirements().items())
                .lecturers(
                        courseInfo.getTutors()
                                .stream()
                                .map(Tutors::fullName)
                                .toList()
                )
                .build();
    }

    // TODO: inspect whether request size could be optimized
    @Override
    public List<ReviewDTO> obtainCourseReviews(String courseId) {
        log.info("Obtaining course reviews for course with id {}", courseId);
        List<CourseReview> fullBunchOfCourseReviews = udemyClient.getCourseReviews(courseId).courses();
        List<CourseReview> reviewsWithContent = fullBunchOfCourseReviews.stream().filter(r -> !r.content().isEmpty()).toList();
        log.info("General amount of course reviews is {}, but responses that have content are {}", fullBunchOfCourseReviews.size(), reviewsWithContent.size());
        return reviewsWithContent.stream()
                .map(element -> ReviewDTO.builder()
                        .rate(element.rate())
                        .content(element.content())
                        .createdAt(element.created())
                        .author(element.reviewer().displayName())
                        .build())
                .limit(3)
                .toList();
    }

    // TODO: inspect whether request size could be optimized
    @Override
    public List<Lecture> getCourseLecture(String courseId) {
        log.info("Getting lectures for course with id {}", courseId);
        List<Lecture> allCourseActivities = udemyClient.getCourseLectures(courseId, REQUIRED_FIELDS_FOR_LECTURE).getLectures();
        List<Lecture> onlyLecturesList = allCourseActivities.stream()
                                                .filter(activity -> activity.type().equals("lecture"))
//                .limit(8)
                .toList();
        log.info("General amount of all activities were {}, but remain {} lectures", allCourseActivities.size(), onlyLecturesList.size());
        return onlyLecturesList;
    }

    @Override
    public DiscountPriceDTO getCourseDiscountedPrice(String courseIds) throws NoSuchCourseException {
        log.info("Get discounted course (ids: {}) price ", courseIds);
        GeneralDiscountCourseInfo courseDiscountPrice = udemyClient.getCourseDiscountPrice(courseIds);
        if (courseDiscountPrice.getDiscountInfo().getPrice() == null) {
            log.info("There is no such course with id {} due to the discount request", courseIds);
            throw new NoSuchCourseException("There is no such course with id " + courseIds);
        }
        return DiscountPriceDTO.builder()
                .commonPrice(courseDiscountPrice.getDiscountInfo().getPrice().normalPrice().getPrice())
                .discountPrice(courseDiscountPrice.getDiscountInfo().getPrice().discountedPrice().getPrice())
                .build();
    }

    @Override
    public List<Course> getHotPropositions() {
        log.info("Getting most popular courses");
        return udemyClient.getBunchOfCourses(tokenCreation.getEncodedToken(), LIMITED_COURSE_FIELDS).getCourses();
    }

    @Override
    public BunchOfCourses getCoursesForCertainArea(String areaName) {
        log.info("Get courses dedicated to {}", areaName);
        return udemyClient.getSpecifiedAreaCourses(areaName, tokenCreation.getEncodedToken(), LIMITED_COURSE_FIELDS);
    }
}
