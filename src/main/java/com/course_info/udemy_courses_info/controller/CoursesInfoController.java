package com.course_info.udemy_courses_info.controller;

import com.course_info.udemy_courses_info.entity.BunchOfCoursesRequest;
import com.course_info.udemy_courses_info.entity.Course;
import com.course_info.udemy_courses_info.entity.DetailedCourseInfo;
import com.course_info.udemy_courses_info.entity.GeneralDiscountCourseInfo;
import com.course_info.udemy_courses_info.exceptions.representation.ExceptionMessage;
import com.course_info.udemy_courses_info.exceptions.NoSuchCourseException;
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

@CrossOrigin("localhost:3000")
@Tag(name = "Various course info", description = "FS2S implementation that provides certain information about Udemy courses")
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
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = DetailedCourseInfo.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionMessage.class), mediaType = "application/json")})
    })
    @GetMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public DetailedCourseInfo certainCourseInfo(
            @Parameter(description = "Course unique ID") @PathVariable String courseId
    ) {
        return coursesInfoService.courseInfo(courseId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = GeneralDiscountCourseInfo.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionMessage.class), mediaType = "application/json")})
    })
    @Operation(
            summary = "Get price with discount by course ID",
            description = "Returns both discounted and regular course price"
    )
    @GetMapping("/{courseId}/discount")
    public GeneralDiscountCourseInfo discountedCoursePrice(
            @Parameter(description = "Unique course id") @PathVariable String courseId
    ) {
        try {
            return coursesInfoService.getCourseDiscountedPrice(courseId);
        } catch (NoSuchCourseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Happens if UdemyAPI is invalid or expired. Another case - UdemyAPI servers are temporarily unavailable", content = {@Content(schema = @Schema(implementation = ExceptionMessage.class), mediaType = "application/json")})
    })
    @Operation(
            summary = "Get 10 popular courses from Udemy",
            description = "Returns courses description with limited information"
    )
    @GetMapping("/hot")
    @ResponseStatus(HttpStatus.OK)
    public List<Course> getHotCourses() {
        return coursesInfoService.getHotPropositions();
    }

    // TODO: remove all special url-associated symbols from IT area (e.g. '/' in UI/UX)  Do it on front-end!
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = BunchOfCoursesRequest.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Happens if UdemyAPI is invalid or expired. Another case - UdemyAPI servers are temporarily unavailable", content = {@Content(schema = @Schema(implementation = ExceptionMessage.class), mediaType = "application/json")})
    })
    @Operation(
            summary = "Returns plenty of courses specialized on certain area",
            description = "According to a user input returns all courses from Udemy that are connected to specific professional field"
    )
    @GetMapping("/search/{certainArea}")
    @ResponseStatus(HttpStatus.OK)
    public BunchOfCoursesRequest getCertainDirectionCourses(
            @Parameter(description = "IT area name (e.g. Java, Python, UI/UX etc.)") @PathVariable String certainArea) {
        return coursesInfoService.getCoursesForCertainArea(certainArea);
    }
}
