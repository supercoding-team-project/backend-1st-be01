package com.github.backend_1st.web.controller;

import com.github.backend_1st.config.security.JwtTokenProvider;
import com.github.backend_1st.service.security.AuthService;
import com.github.backend_1st.web.dto.auth.LoginDTO;
import com.github.backend_1st.web.dto.auth.LogoutDTO;
import com.github.backend_1st.web.dto.auth.SignUpDTO;
import com.github.backend_1st.web.dto.auth.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class SignController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/signup")
    public Map<String, String> register(@RequestBody SignUpDTO signUpRequest) {
        return authService.signUp(signUpRequest);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginRequest) {
        TokenDTO tokenDTO = authService.login(loginRequest);
        Map<String, String> responseMessage = new HashMap<>();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        responseHeaders.set("Authorization", tokenDTO.getAccessToken());

        responseMessage.put("message", "로그인이 성공적으로 완료되었습니다.");

        return new ResponseEntity<>(responseMessage, responseHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Map<String, String>> login(@RequestBody LogoutDTO logoutDTO) {
        Map<String, String> responseMessage = authService.logout(logoutDTO);
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<>(responseMessage, responseHeaders, HttpStatus.OK);
    }
}
