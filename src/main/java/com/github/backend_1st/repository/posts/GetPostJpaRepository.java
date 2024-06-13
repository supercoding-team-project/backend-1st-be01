package com.github.backend_1st.repository.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GetPostJpaRepository extends JpaRepository<PostEntity, Integer>{


    List<PostEntity> findByUserId(Integer id);
}
