package com.github.backend_1st.service.postService;

import com.github.backend_1st.repository.posts.GetPostJpaRepository;
import com.github.backend_1st.repository.posts.PostEntity;
import com.github.backend_1st.repository.users.UserEntity;
import com.github.backend_1st.repository.users.UserJpaRepository;
import com.github.backend_1st.service.mapper.CreatePostMapper;
import com.github.backend_1st.web.dto.CreatePost;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePostService {

    private  final UserJpaRepository userJpaRepository;
    private final GetPostJpaRepository getPostJpaRepository;

    public void savePost(CreatePost createPost) {
        //작성자로 유저정보 가저오기
        Optional<UserEntity> userEntity = userJpaRepository.findByEmail(createPost.getAuthor());
        UserEntity userFound = userEntity.get();
        PostEntity postEntity = CreatePostMapper.INSTANSE.idAndCreatePostToPostEntity(createPost);
        postEntity.setUser(userFound);
        log.info("PostEntity - ID: {}, User: {}, Title: {}, Content: {}", postEntity.getId(), postEntity.getUser(), postEntity.getTitle(), postEntity.getContent());

        getPostJpaRepository.save(postEntity);
    }
}
