package org.example.eco_v2.common.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.example.eco_v2.common.response.CommonResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerMapping;

import java.time.LocalDateTime;

import static org.example.eco_v2.common.ExcMessage.*;

@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {
    private final HandlerMapping resourceHandlerMapping;

    public MyExceptionHandler(@Qualifier("resourceHandlerMapping") HandlerMapping resourceHandlerMapping) {
        this.resourceHandlerMapping = resourceHandlerMapping;
    }

    @ExceptionHandler(OtpException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse handleEarlyResentException(OtpException e) {
        return new CommonResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(IncorrectPassword.class)
    public ResponseEntity<?> handleException(IncorrectPassword e) {
        log.error(INVALID_PASSWORD, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new CommonResponse(INVALID_PASSWORD, LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value()));
    }


    @ExceptionHandler(TimeOut.class)
    public ResponseEntity<?> handleException(TimeOut e) {
        log.error(TIME_OUT, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new CommonResponse(TIME_OUT, LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(RedisConnectionFailureException.class)
    public ResponseEntity<String> handleException(RedisConnectionFailureException e) {
        log.error(REDIS_ERROR, e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleUsernameNotFound(BadCredentialsException e) {
        log.error("Username or password is not correct{}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CommonResponse("Username or password is not correct",
                        LocalDateTime.now(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(WrongInputException.class)
    public ResponseEntity<?> handleWrongException(WrongInputException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(401)
                .body(new CommonResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value()));
    }

}
