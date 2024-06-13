package com.github.backend_1st.web.dto.comments;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
@ToString
public class Comment {
    private Integer id;
    private String content;
    private String author;
    private Integer postId;
    private LocalDateTime createdAt;
}
