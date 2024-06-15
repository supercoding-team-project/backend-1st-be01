package com.github.backend_1st.repository.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeJpaRepository extends JpaRepository<LikeEntity, Integer> {
}
