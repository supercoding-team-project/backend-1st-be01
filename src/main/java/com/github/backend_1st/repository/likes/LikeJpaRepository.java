package com.github.backend_1st.repository.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeJpaRepository extends JpaRepository<LikeEntity, Integer> {
    List<LikeEntity> findByCommentId(Integer commentIdInt);

    Optional<LikeEntity> findByCommentIdAndUserId(Integer commentIdInt, Integer userId);
}
