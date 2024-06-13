package com.github.backend_1st.service.postService;

import com.github.backend_1st.repository.posts.GetPostJpaRepository;
import com.github.backend_1st.repository.posts.PostEntity;
import com.github.backend_1st.repository.users.UserEntity;
import com.github.backend_1st.repository.users.UserJpaRepository;
import com.github.backend_1st.service.mapper.GetPostMapper;
import com.github.backend_1st.web.dto.GetPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetPostService {
    private final GetPostJpaRepository getPostJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public void deletePost(String id) {
        Integer idInt = Integer.parseInt(id);
        getPostJpaRepository.deleteById(idInt);
    }

    public List<GetPost> findAllPost() {
        List<PostEntity> postEntities = getPostJpaRepository.findAll();
        return postEntities.stream().map(GetPostMapper.INSTANCE::postEntityToPost).collect(Collectors.toList());
    }

    public List<GetPost> findPostByEmail(String email) {
        //이메일로 유저 가져오기
        UserEntity userEntity = userJpaRepository.findByEmail(email);
        //유저 아이디로 포스트 가져오기
        List<PostEntity> postEntities = getPostJpaRepository.findByUserId(userEntity.getId()); //JWT에서 가져오기

        return postEntities.stream().map(GetPostMapper.INSTANCE::postEntityToPost).collect(Collectors.toList());
    }
}
