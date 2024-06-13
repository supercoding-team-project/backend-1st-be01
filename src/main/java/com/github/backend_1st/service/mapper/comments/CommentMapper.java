package com.github.backend_1st.service.mapper.comments;

import com.github.backend_1st.repository.comments.CommentEntity;
import com.github.backend_1st.web.dto.comments.Comment;
import com.github.backend_1st.web.dto.comments.CommentBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
@Mapping(source = "user.email", target = "author")
@Mapping(source = "post.id", target = "postId")
Comment commentEntityToComment(CommentEntity commentEntity);
@Mapping(source = "author", target = "user.email")
@Mapping(source = "postId", target = "post.id")
CommentEntity commentBodyToCommentEntity(CommentBody commentBody);
}
