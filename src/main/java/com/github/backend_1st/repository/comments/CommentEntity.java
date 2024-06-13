package com.github.backend_1st.repository.comments;

import com.github.backend_1st.repository.posts.PostEntity;
import com.github.backend_1st.repository.users.UserEntity;
import com.github.backend_1st.web.dto.comments.CommentBody;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Builder
@Table(name = "comments")
public class CommentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content" , length = 1000 , nullable = false)
    private String content;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public void setCommentBody(CommentBody commentBody){
        this.content = commentBody.getContent();
    }
}
