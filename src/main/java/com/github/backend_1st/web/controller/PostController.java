package com.github.backend_1st.web.controller;

import com.github.backend_1st.web.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/posts")
public class PostController {

    private final String secretKey = Base64.getEncoder().encodeToString("spring-bootcamp-super-coding-first-project".getBytes());

    @GetMapping(value = "/search")
    public void searchAndGet(HttpServletRequest request) {
        Claims claims = JwtUtil.getCurrentUser(JwtUtil.getToken(request), secretKey);

        System.out.println("claims = " + claims);
    }
}
