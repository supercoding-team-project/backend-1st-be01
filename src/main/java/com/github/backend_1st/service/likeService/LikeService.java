package com.github.backend_1st.service.likeService;

import com.github.backend_1st.repository.comments.CommentEntity;
import com.github.backend_1st.repository.comments.CommentJpaRepository;
import com.github.backend_1st.repository.likes.LikeEntity;
import com.github.backend_1st.repository.likes.LikeJpaRepository;
import com.github.backend_1st.repository.users.UserEntity;
import com.github.backend_1st.repository.users.UserJpaRepository;
import com.github.backend_1st.service.mapper.likes.LikeMapper;
import com.github.backend_1st.web.dto.likes.Likes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeJpaRepository likeJpaRepository;
    private final CommentJpaRepository commentJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public List<Likes> findLikeByCommentId(String commentId) {
        Integer commentIdInt = Integer.valueOf(commentId);
        List<LikeEntity> likeEntities = likeJpaRepository.findByCommentId(commentIdInt);
        return likeEntities.stream().map(LikeMapper.INSTANCE::likeEntityToLikes).collect(Collectors.toList());
    }

    public String saveLike(String commentId, Integer userId) {
        Integer commentIdInt = Integer.valueOf(commentId);
        CommentEntity comment = commentJpaRepository.findById(commentIdInt)
                .orElseThrow(() -> new RuntimeException("Comment can not found"));
        UserEntity user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User can not found"));
        LikeEntity likeEntity = LikeEntity.builder()
                .comment(comment)
                .user(user)
                .build();
        likeJpaRepository.save(likeEntity);
        return "like post success";
    }
}
