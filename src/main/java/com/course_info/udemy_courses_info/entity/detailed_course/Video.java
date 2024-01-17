package com.course_info.udemy_courses_info.entity.detailed_course;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Video(
   String label,
   @JsonAlias({"file"})
   String videoURL
) {}
