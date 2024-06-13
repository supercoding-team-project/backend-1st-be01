package com.github.backend_1st.service.mapper;

import com.github.backend_1st.repository.posts.GetPostJpaRepository;
import com.github.backend_1st.repository.posts.PostEntity;
import com.github.backend_1st.repository.users.UserEntity;
import com.github.backend_1st.repository.users.UserJpaRepository;
import com.github.backend_1st.web.dto.CreatePost;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Optional;

@Mapper
public interface CreatePostMapper {

    CreatePostMapper INSTANSE = Mappers.getMapper(CreatePostMapper.class);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "user.email", source = "author")
    PostEntity idAndCreatePostToPostEntity(CreatePost createPost);
}



