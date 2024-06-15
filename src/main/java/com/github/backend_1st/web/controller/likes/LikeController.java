package com.github.backend_1st.web.controller.likes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LikeController {
    private final LikeService likeService;
    @GetMapping("/likes/{commentId}")
    public LikeResponse findLikeByCommentId(@PathVariable String commentId){
        List<Likes> likes = likeService.findLikeByCommentId();
        return new LikeResponse(likes);
    }

}
