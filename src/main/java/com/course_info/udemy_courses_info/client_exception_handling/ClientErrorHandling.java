package com.course_info.udemy_courses_info.client_exception_handling;

import com.course_info.udemy_courses_info.exceptions.representation.ExceptionMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
public class ClientErrorHandling implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        ExceptionMessage message;
        try (InputStream stream = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(stream, ExceptionMessage.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }

        log.info("Feign client request: {}", s);
        switch (response.status()) {
            case 400 -> {
                log.info("Bad request was executed to {}", s);
                return new ResponseStatusException(HttpStatus.BAD_REQUEST, message.getMessage() != null ? message.getMessage() : "Your request was insufficient. Please, verify it one more time");
            }
            case 403 -> {
                log.error("UdemyAPI token is expired or invalid. Request: {}", s);
                return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message.getMessage() != null ? message.getMessage() : "UdemyAPI token is invalid or expired. Please report this to application developers");
            }
            case 404 -> {
                log.info("Resource wasn't found in response to {}", s);
                return new ResponseStatusException(HttpStatus.NOT_FOUND, message.getMessage() != null ? message.getMessage() : "Unable to find certain info");
            }
            case 500 -> {
                log.warn("UdemyAPI request server isn't working properly. Please try again later");
                return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message.getMessage() != null ? message.getMessage() : "UdemyAPI server is temporarily not working. Please try again later");
            }
            default -> {
                return errorDecoder.decode(s, response);
            }
        }
    }
}
