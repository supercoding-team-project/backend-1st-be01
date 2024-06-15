package com.github.backend_1st.service.likeService;

import com.github.backend_1st.repository.likes.LikeEntity;
import com.github.backend_1st.repository.likes.LikeJpaRepository;
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

    public List<Likes> findLikeByCommentId(String commentId) {
        Integer commentIdInt = Integer.valueOf(commentId);
        List<LikeEntity> likeEntities = likeJpaRepository.findByCommentId(commentIdInt);
        return likeEntities.stream().map(LikeMapper.INSTANCE::likeEntityToLikes).collect(Collectors.toList());
    }
}
