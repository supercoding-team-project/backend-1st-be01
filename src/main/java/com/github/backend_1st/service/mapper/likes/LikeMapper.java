package com.github.backend_1st.service.mapper.likes;

import com.github.backend_1st.repository.likes.LikeEntity;
import com.github.backend_1st.web.dto.likes.Likes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LikeMapper {
LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);

@Mapping(source = "user.email", target = "author")
    Likes likeEntityToLikes(LikeEntity likeEntity);

}
