package com.course_info.udemy_courses_info.controller;

import com.course_info.udemy_courses_info.dto.*;
import com.course_info.udemy_courses_info.entity.courses.BunchOfCourses;
import com.course_info.udemy_courses_info.entity.courses.Course;
import com.course_info.udemy_courses_info.entity.lectures.Lecture;
import com.course_info.udemy_courses_info.exceptions.NoSuchCourseException;
import com.course_info.udemy_courses_info.exceptions.representation.ExceptionMessage;
import com.course_info.udemy_courses_info.services.CoursesInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//TODO: inspect whether cors could optimized
@CrossOrigin
@Tag(name = "API dedicated to Udemy courses", description = "FS2S provides multiple methods to obtain info dedicated to Udemy courses")
@RestController
@RequestMapping("/roadmaps/courses-info")
@RequiredArgsConstructor
public class CoursesInfoController {

    private final CoursesInfoService coursesInfoService;


    @Operation(
            summary = "Course information by id ID",
            description = "Obtain full information about certain course by providing its identifier"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = DetailedCourseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionMessage.class), mediaType = "application/json")})
    })
    @GetMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public DetailedCourseDTO certainCourseInfo(
            @Parameter(description = "Course unique ID") @PathVariable String courseId
    ) {
        return coursesInfoService.courseInfo(courseId);
    }

    @Operation(
            summary = "Reviews on course by its ID",
            description = "Obtain comments that have a content (text review) dedicated to a certain course"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ReviewDTO.class)), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionMessage.class), mediaType = "application/json")})
    })
    @GetMapping("/{courseId}/reviews")
    public List<ReviewDTO> obtainCourseComments(@Parameter(description = "Unique course id") @PathVariable String courseId) {
        return coursesInfoService.obtainCourseReviews(courseId);
    }

    @Operation(
            summary = "Get a few course lectures (maximum 12)",
            description = "Returns selected course activities, especially lectures"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Lecture.class)), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionMessage.class), mediaType = "application/json")})
    })
    @GetMapping("/{courseId}/lectures")
    public List<Lecture> obtainCourseLectures(@Parameter(description = "Unique course id") @PathVariable String courseId) {
        return coursesInfoService.getCourseLecture(courseId);
    }

    @Operation(
            summary = "DEPRECATED. DO NOT USE!!!         Get discountPrice with discount by course ID",
            description = "Returns both discounted and regular course discountPrice"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = DiscountPriceDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionMessage.class), mediaType = "application/json")})
    })
    @GetMapping("/{courseId}/discount")
    public DiscountPriceDTO discountedCoursePrice(
            @Parameter(description = "Unique course id") @PathVariable String courseId
    ) {
        try {
            return coursesInfoService.getCourseDiscountedPrice(courseId);
        } catch (NoSuchCourseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(
            summary = "Get 10 popular courses from Udemy",
            description = "Returns courses description with limited information"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Happens if UdemyAPI is invalid or expired. Another case - UdemyAPI servers are temporarily unavailable", content = {@Content(schema = @Schema(implementation = ExceptionMessage.class), mediaType = "application/json")})
    })
    @GetMapping("/hot")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseDTO> getHotCourses() {
        return coursesInfoService.getHotPropositions();
    }

    // TODO: remove all special url-associated symbols from IT area (e.g. '/' in UI/UX)  Do it on front-end!
    @Operation(
            summary = "Returns plenty of courses specialized on certain area",
            description = "According to a user input returns all courses from Udemy that are connected to specific professional field"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = BunchOfCourses.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Happens if UdemyAPI is invalid or expired. Another case - UdemyAPI servers are temporarily unavailable", content = {@Content(schema = @Schema(implementation = ExceptionMessage.class), mediaType = "application/json")})
    })
    @GetMapping("/search/{certainArea}")
    @ResponseStatus(HttpStatus.OK)
    public CertainAreaCoursesDTO getCertainDirectionCourses(
            @Parameter(description = "IT area name (e.g. Java, Python, UI/UX etc.)") @PathVariable String certainArea) {
        return coursesInfoService.getCoursesForCertainArea(certainArea);
    }
}
