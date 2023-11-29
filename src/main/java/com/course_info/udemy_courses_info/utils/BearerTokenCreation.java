package com.course_info.udemy_courses_info.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class BearerTokenCreation {

    @Value("${udemy-api.client.id}")
    private String clientId;

    @Value("${udemy-api.client.secret}")
    private String clientSecret;

    private String bearerToken;

    public String getEncodedToken() {
        if (bearerToken != null)
            return bearerToken;

        bearerToken = "Basic " + Base64.getEncoder().encodeToString(
                (clientId + ":" + clientSecret).getBytes()
        );

        return bearerToken;
    }
}
