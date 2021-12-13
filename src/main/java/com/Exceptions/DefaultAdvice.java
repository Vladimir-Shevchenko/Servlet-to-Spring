package com.Exceptions;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ThemeResolver;

import java.util.Arrays;


@ControllerAdvice
@Slf4j
public class DefaultAdvice {

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Throwable e) {
        final String modelObject = Arrays.toString(e.getStackTrace());
        log.error("error: ", modelObject);
        return new ModelAndView("error", "errorMessage", modelObject);
    }

}