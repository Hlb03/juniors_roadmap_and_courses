package com.course_info.udemy_courses_info.exceptions.representation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionMessage {
    private String detail;
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
