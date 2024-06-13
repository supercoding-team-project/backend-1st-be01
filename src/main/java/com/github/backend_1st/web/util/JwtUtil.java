package com.github.backend_1st.web.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

public class JwtUtil {

    public static String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public static Claims getCurrentUser(String token, String secretKey) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }
}
