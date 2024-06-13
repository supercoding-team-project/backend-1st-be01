package com.github.backend_1st.service.security;

import com.github.backend_1st.config.security.JwtTokenProvider;
import com.github.backend_1st.repository.roles.Roles;
import com.github.backend_1st.repository.roles.RolesRepository;
import com.github.backend_1st.repository.userPrincipal.UserPrincipal;
import com.github.backend_1st.repository.userPrincipal.UserPrincipalRepository;
import com.github.backend_1st.repository.userPrincipal.UserPrincipalRoles;
import com.github.backend_1st.repository.userPrincipal.UserPrincipalRolesRepository;
import com.github.backend_1st.repository.users.UserEntity;
import com.github.backend_1st.repository.users.UserJpaRepository;
import com.github.backend_1st.web.dto.auth.LoginDTO;
import com.github.backend_1st.web.dto.auth.LogoutDTO;
import com.github.backend_1st.web.dto.auth.SignUpDTO;
import com.github.backend_1st.web.dto.auth.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserPrincipalRepository userPrincipalRepository;
    private final UserPrincipalRolesRepository userPrincipalRolesRepository;
    private final UserJpaRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public Map<String, String> signUp(SignUpDTO signUpRequest) {
        Map<String, String> messageMap = new HashMap<>();

        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();

        if(userPrincipalRepository.existsByEmail(email)) {
            messageMap.put("message", "회원가입에 실패했습니다. 이미 존재하는 회원입니다.");
        }

        UserEntity userFound = userRepository.findByEmail(email)
                                                .orElseGet(() -> userRepository.save(UserEntity.builder()
                                                                                               .email(email)
                                                                                               .password(password) //TODO : password 암호화하기
                                                                                               .build()));

        Roles roles = rolesRepository.findByName("ROLE_USER")
                                        .orElseThrow(() -> new RuntimeException("ROLE_USER를 찾을 수가 없습니다."));

        UserPrincipal userPrincipal = UserPrincipal.builder()
                                                    .email(email)
                                                    .user(userFound)
                                                    .password(passwordEncoder.encode(password))
                                                    .build();

        userPrincipalRepository.save(userPrincipal);
        userPrincipalRolesRepository.save(
                UserPrincipalRoles.builder()
                                  .roles(roles)
                                  .userPrincipal(userPrincipal)
                                  .build()
        );

        messageMap.put("message", "회원가입이 완료되었습니다.");

        return messageMap;
    }

    public TokenDTO login(LoginDTO loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            UserPrincipal userPrincipal = userPrincipalRepository.findByEmailFetchJoin(email)
                    .orElseThrow(() -> new RuntimeException("UserPrincipal을 찾을 수 없습니다."));

            Integer userPrincipalId = userPrincipal.getUserPrincipalId();

            List<String> roles = userPrincipal.getUserPrincipalRoles()
                                                .stream()
                                                .map(UserPrincipalRoles::getRoles)
                                                .map(Roles::getName)
                                                .collect(Collectors.toList());

            String accessToken = jwtTokenProvider.createToken(email, roles, userPrincipalId);
            TokenDTO tokenDTO = new TokenDTO(accessToken);

            return tokenDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("로그인 할 수 없습니다.");
        }
    }

    public Map<String, String> logout(LogoutDTO logoutDTO) {
        Map<String, String> messageMap = new HashMap<>();
        String email = logoutDTO.getEmail();

        if(!userPrincipalRepository.existsByEmail(email))
            throw new RuntimeException("유저를 찾을 수 없습니다.");

        messageMap.put("message", "로그아웃되었습니다.");
        return messageMap;
    }
}
