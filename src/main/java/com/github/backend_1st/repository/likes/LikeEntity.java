package com.github.backend_1st.repository.likes;

import com.github.backend_1st.repository.comments.CommentEntity;
import com.github.backend_1st.repository.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Table(name = "likes")
public class LikeEntity {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
@ManyToOne
@JoinColumn(name = "comment_id")
private CommentEntity comment;

@ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
