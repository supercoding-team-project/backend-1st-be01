package com.github.backend_1st.service.postService;

import com.github.backend_1st.repository.posts.GetPostJpaRepository;
import com.github.backend_1st.repository.posts.PostEntity;
import com.github.backend_1st.repository.users.UserJpaRepository;
import com.github.backend_1st.web.dto.UpdatePost;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdatePostService {
    private final GetPostJpaRepository getPostJpaRepository;
    private final UserJpaRepository userJpaRepository;


    public void updatePost(String id, UpdatePost updatePost) {
        //게시글아이디로 게시글 찾기
        Integer idInt = Integer.parseInt(id);
        PostEntity postEntity = getPostJpaRepository.findById(idInt).
                orElseThrow(() -> new EntityNotFoundException("ID에 해당하는 게시물을 찾을 수 없습니다: " + idInt));;

        String updatedTitle = updatePost.getTitle();
        String updatedContent = updatePost.getContent();

        postEntity.setTitle(updatedTitle);
        postEntity.setContent(updatedContent);
        LocalDateTime updatedTime = LocalDateTime.now();
        postEntity.setCreatedAt(updatedTime);

        getPostJpaRepository.save(postEntity);
    }
}
