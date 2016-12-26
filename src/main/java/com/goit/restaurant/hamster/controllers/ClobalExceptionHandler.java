package com.goit.restaurant.hamster.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ClobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request, Exception ex){
        request.setAttribute("exception", ex);
        return "error";
    }
}
