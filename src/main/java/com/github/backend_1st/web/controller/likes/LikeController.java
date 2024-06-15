package com.github.backend_1st.web.controller.likes;

import com.github.backend_1st.service.likeService.LikeService;
import com.github.backend_1st.web.dto.likes.LikeResponse;
import com.github.backend_1st.web.dto.likes.Likes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    @GetMapping("/likes/{commentId}")
    public LikeResponse findLikeByCommentId(@PathVariable String commentId){
        List<Likes> likes = likeService.findLikeByCommentId(commentId);
        return new LikeResponse(likes);
    }

}
