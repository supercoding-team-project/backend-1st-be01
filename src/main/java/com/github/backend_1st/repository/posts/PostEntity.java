package com.github.backend_1st.repository.posts;

import com.github.backend_1st.repository.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@AllArgsConstructor
@Table(name="posts")
public class PostEntity {
    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name="content", length = 1000)
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}