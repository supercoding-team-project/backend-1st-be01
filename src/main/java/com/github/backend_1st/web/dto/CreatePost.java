package com.github.backend_1st.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreatePost {

    private String title;
    private String content;
    private String author;

}
