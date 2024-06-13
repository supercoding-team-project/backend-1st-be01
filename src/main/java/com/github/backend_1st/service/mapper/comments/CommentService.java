package com.github.backend_1st.service.mapper.comments;

import com.github.backend_1st.repository.comments.CommentEntity;
import com.github.backend_1st.repository.comments.CommentJpaRepository;
import com.github.backend_1st.repository.users.UserEntity;
import com.github.backend_1st.repository.users.UserJpaRepository;
import com.github.backend_1st.web.dto.comments.Comment;
import com.github.backend_1st.web.dto.comments.CommentBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentJpaRepository commentJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public Integer findUserIdByEmail(CommentBody commentBody){
        String userEmail = commentBody.getAuthor();
        Optional<UserEntity> user = userJpaRepository.findByEmail(userEmail);
        return user != null ? user.get().getId() : null;
    }
    public List<Comment> findAllComment() {
        List<CommentEntity> commentEntities = commentJpaRepository.findAll();
        return commentEntities.stream().map(CommentMapper.INSTANCE::commentEntityToComment).collect(Collectors.toList());
    }

    @Transactional
    public String saveComment(CommentBody commentBody) {
        Integer userId = findUserIdByEmail(commentBody);
        if (userId == null) {
            return "사용자를 찾을 수 없습니다.";
        }
        UserEntity user = userJpaRepository.findById(userId).orElse(null);
        if (user == null) {
            return "사용자 정보를 불러올 수 없습니다.";
        }
        CommentEntity commentEntity = CommentMapper.INSTANCE.commentBodyToCommentEntity(commentBody);
        commentEntity.setUser(user);
        try {
            // 댓글 엔티티 저장
            CommentEntity commentEntityCreated = commentJpaRepository.save(commentEntity);
            String result = "댓글이 성공적으로 작성되었습니다.";
            return result;
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            return "댓글 작성 중 오류가 발생했습니다.";
        }
    }

    @Transactional
    public String updateComment(String id, CommentBody commentBody) {
        Integer idInt = Integer.valueOf(id);
        CommentEntity commentEntity = commentJpaRepository.findById(idInt).orElseThrow(()-> new RuntimeException("아이디를 불러오는데 실패했습니다."));
        commentEntity.setContent(commentBody.getContent());
        try {
            commentJpaRepository.save(commentEntity);
            String result = "댓글이 성공적으로 수정되었습니다.";
            return result;
        }catch (RuntimeException e){
            e.printStackTrace();
            return "댓글 수정 중 오류가 발생했습니다.";
        }
    }
}
