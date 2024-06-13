package com.github.backend_1st.service.mapper;

//import com.github.backend_1st.repository.users.UserEntity;
//import com.github.backend_1st.repository.users.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserMapperHelper {

    //private final UserJpaRepository userJpaRepository;

//    @Named("authorToEmail")
//    public String authorToEmail(String author) {
//
//        UserEntity userEntity = userJpaRepository.findByEmail(author);
//        return userEntity != null ? userEntity.getEmail() : null;
//    }
}
