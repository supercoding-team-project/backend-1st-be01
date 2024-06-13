package com.github.backend_1st.service.postService;

import com.github.backend_1st.repository.posts.GetPostJpaRepository;
import com.github.backend_1st.repository.posts.PostEntity;
import com.github.backend_1st.repository.users.UserEntity;
import com.github.backend_1st.repository.users.UserJpaRepository;
import com.github.backend_1st.service.mapper.CreatePostMapper;
import com.github.backend_1st.web.dto.CreatePost;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePostService {

    private  final UserJpaRepository userJpaRepository;
    private final GetPostJpaRepository getPostJpaRepository;

    //userEntity : id, email, password, nickname
    //id, user, content, title, createAt
    //createPost타입에서 PostEntity로 바꿔 저장
    public void savePost(CreatePost createPost) { //title, content, author
        //작성자로 유저정보 가저오기
        UserEntity userEntity = userJpaRepository.findByEmail(createPost.getAuthor());
        log.info("UserEntity - ID: {}, Email: {}, Nickname: {}", userEntity.getId(), userEntity.getEmail(),userEntity.getNickName());
        String title = createPost.getTitle();
        String content = createPost.getContent();
        PostEntity postEntity = CreatePostMapper.INSTANSE.idAndCreatePostToPostEntity(null,userEntity,title,content);
        log.info("PostEntity - ID: {}, User: {}, Title: {}, Content: {}", postEntity.getId(), postEntity.getUser(), postEntity.getTitle(), postEntity.getContent());

        //id, 시간, user(author -> userEntity)
        // null,   //id=5, email= hoi     //title        //content

        getPostJpaRepository.save(postEntity);
    }
}

//PostEntity postEntity = CreatePostMapper.INSTANSE.idAndCreatePostToPostEntity(1,createPost);

//@Id
//@Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
//private Integer id;
//
//@ManyToOne(fetch = FetchType.LAZY)
//@JoinColumn(name = "user_id")
//private UserEntity user;
//
//@Column(name = "title", length = 100)
//private String title;
//
//@Column(name="content", length = 1000)
//private String content;
//
//@Column(name = "created_at")
//private LocalDateTime createdAt;
