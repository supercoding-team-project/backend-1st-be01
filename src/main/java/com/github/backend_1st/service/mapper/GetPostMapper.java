package com.github.backend_1st.service.mapper;

import com.github.backend_1st.repository.posts.PostEntity;
import com.github.backend_1st.web.dto.GetPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public interface GetPostMapper {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    GetPostMapper INSTANCE = Mappers.getMapper(GetPostMapper.class);

    @Mapping(target = "author", source="postEntity.user.email")
    @Mapping(target = "createdAt", source= "postEntity.createdAt", qualifiedByName = "convert")
    GetPost postEntityToPost(PostEntity postEntity);

    @Named("convert")
    static String localDateTimeToString(LocalDateTime localDateTime) {
        if (localDateTime != null) return localDateTime.format(formatter);
        else return null;
    }
}
