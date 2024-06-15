package com.github.backend_1st.web.dto.likes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponse {
    private List<Likes> likes;
}
