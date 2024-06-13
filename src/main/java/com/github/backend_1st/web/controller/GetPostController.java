package com.github.backend_1st.web.controller;

import com.github.backend_1st.service.postService.CreatePostService;
import com.github.backend_1st.service.postService.GetPostService;
import com.github.backend_1st.service.postService.UpdatePostService;
import com.github.backend_1st.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GetPostController {

    private final GetPostService getPostService;
    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;

    @GetMapping("/posts")
    public GetPostResponse findAllPost(){
        List<GetPost> posts = getPostService.findAllPost();
        return new GetPostResponse(posts);
    }

    @GetMapping("/posts/search")
    public GetPostResponse findPostByEmail(@RequestParam("email") String email){
        List<GetPost> posts = getPostService.findPostByEmail(email);
        return new GetPostResponse(posts);
    }

    @DeleteMapping("/posts/{id}")
    public String deletePostById(@PathVariable String id){
        getPostService.deletePost(id);
        String responseMessage = "게시물 ID:"+ id + " 가 성공적으로 삭제되었습니다.";
        return responseMessage;
    }

//    @PostMapping("/posts")
//    public PostResponse createPost(@RequestBody CreatePost createPost){
//        createPostService.savePost(createPost);
//        return new PostResponse("게시물이 성공적으로 작성되었습니다.");
//    }

//    @PostMapping("/posts")
//    public PostResponse createPost(@RequestBody CreatePost createPost){ // title, content, author
//        createPostService.savePost(createPost);
//        return new PostResponse("게시물이 성공적으로 작성되었습니다.");
//    }


    @PostMapping("/posts")
    public ResponseEntity<PostResponse> createPost(@RequestBody CreatePost createPost) {
        createPostService.savePost(createPost);
        return ResponseEntity.ok(new PostResponse("게시물이 성공적으로 작성되었습니다."));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable String id, @RequestBody UpdatePost updatePost){
        updatePostService.updatePost(id, updatePost);
        return ResponseEntity.ok(new PostResponse("게시물이 성공적으로 수정되었습니다."));
    }
}
