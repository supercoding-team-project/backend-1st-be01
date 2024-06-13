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

//@Mapper(componentModel = "spring", uses = {UserMapperHelper.class})
@Mapper
public interface CreatePostMapper {

    CreatePostMapper INSTANSE = Mappers.getMapper(CreatePostMapper.class);

//    @Mapping(target = "user.email", source = "createPost.author", qualifiedByName = "authorToEmail")
    //@Mapping(target = "user.email", source = "createPost.author")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "user", source = "userEntity")
    PostEntity idAndCreatePostToPostEntity(Integer id ,UserEntity userEntity, String title, String content);
                                          // null,   //id=5, email= hoi     //title        //content
}

//private String title;
//private String content;
//private String author;

//userEntity
//private Integer id;
//
//private String email;
//
//private String password;
//
//private String nickName;


//PostEntity
//private Integer id;

//private UserEntity user;
//
//private String title;
//
//private String content;
//
//private LocalDateTime createdAt;


