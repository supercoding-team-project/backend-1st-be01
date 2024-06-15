package com.github.backend_1st.web.controller.likes;

import com.github.backend_1st.config.security.JwtTokenProvider;
import com.github.backend_1st.service.likeService.LikeService;
import com.github.backend_1st.web.dto.likes.LikeResponse;
import com.github.backend_1st.web.dto.likes.Likes;
import com.github.backend_1st.web.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${spring.jwt.secret}")
    private String secretKey;
    @GetMapping("/likes/{commentId}")
    public LikeResponse findLikeByCommentId(@PathVariable String commentId){
        List<Likes> likes = likeService.findLikeByCommentId(commentId);
        return new LikeResponse(likes);
    }
    @PostMapping("/likes/{commentId}")
    public ResponseEntity<Map<String , String>> addLike(@PathVariable String commentId , HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Integer userId = jwtTokenProvider.getUserIdFromToken(token); // 사용자 ID 추출
            String returnMessage = likeService.saveLike(commentId, userId);
            Map<String, String> response = new HashMap<>();
            response.put("message", returnMessage);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid or expired token"));
        }
    }

}
