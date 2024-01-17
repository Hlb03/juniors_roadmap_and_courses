package com.course_info.udemy_courses_info.entity.detailed_course;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;
import java.util.Map;


public record PromoVideo (
        @JsonAlias({"Video"})
        List<Video> videos
){}

//@Data
//public class PromoVideo {
//
//    @JsonAlias({"Video"})
////    private Object json;
////    private JSONArray json;
//    private List<Video> videos;
//
////    @JsonProperty("Video")
////    private String checkObjectMapper(Map<String, Object> json) {
////        try {
////            ObjectMapper objectMapper = new ObjectMapper();
////
////            // Parse the JSON array
////            JsonNode jsonArray = objectMapper.convertValue(json, String.class);
////
////            // Check if it's an array
////            if (jsonArray.isArray()) {
////                // Iterate over the first two elements
////                for (int i = 0; i < 2 && i < jsonArray.size(); i++) {
////                    JsonNode element = jsonArray.get(i);
////                    System.out.println("Element " + (i + 1) + ": " + element);
////                }
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//
////    @JsonAlias({"Video"})
////    private void mapNestedLocale(Map<String, Object> json) {
////        System.out.println("All objects: " + json.keySet());
////
////        this.json = json.get("Video");
////    }
//}
