package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {

        model.addAttribute("message", "잘못된 요청입니다. 다시 시도해주세요.");
        return "error_page/article_type_error"; 
    }
}
