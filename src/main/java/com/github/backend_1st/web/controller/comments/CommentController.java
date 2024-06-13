package com.github.backend_1st.web.controller.comments;

import com.github.backend_1st.service.mapper.comments.CommentService;
import com.github.backend_1st.web.dto.comments.Comment;
import com.github.backend_1st.web.dto.comments.CommentBody;
import com.github.backend_1st.web.dto.comments.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comments")
    public CommentResponse findAllComment(){
        List<Comment> comments = commentService.findAllComment();
        return new CommentResponse(comments);
    }
    @PostMapping("/comments")
    public ResponseEntity<Map<String, String>> addOneComment(@RequestBody CommentBody commentBody){
        String returnMessage = commentService.saveComment(commentBody);
        Map<String, String> response = new HashMap<>();
        response.put("message", returnMessage);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/comments/{id}")
    public ResponseEntity<Map<String,String>> updateComment(@PathVariable String id, @RequestBody CommentBody commentBody){
        String returnMessage = commentService.updateComment(id , commentBody);
        Map<String , String> response = new HashMap<>();
        response.put("message",returnMessage);
        return ResponseEntity.ok(response);
    }

}
