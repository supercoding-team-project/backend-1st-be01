package com.github.backend_1st.repository.userPrincipal;

import com.github.backend_1st.repository.roles.Roles;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user_principal_roles")
public class UserPrincipalRoles {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_principal_role_id")
    private Integer userPrincipalRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_principal_id")
    private UserPrincipal userPrincipal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Roles roles;

}


