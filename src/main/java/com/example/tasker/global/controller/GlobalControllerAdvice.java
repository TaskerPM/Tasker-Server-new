package com.example.tasker.global.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("accessToken")
    public String getAccessToken(HttpServletRequest request) {
        return (String) request.getAttribute("accessToken");
    }

    @ModelAttribute
    public void addHeaders(@ModelAttribute("accessToken") String accessToken, HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("access_token", accessToken);
        response.addHeader("access_token", accessToken);
    }

}

