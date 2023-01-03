package com.chalchal.chalchalsever.domain;

import com.chalchal.chalchalsever.enumeration.UserRole;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@Table(name = "t_user")
@NoArgsConstructor
@DynamicUpdate
public class User extends BaseDomain implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "intro")
    private String intro;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "use_yn", nullable = false)
    private String useYn;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRole.getValue()));
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void changeUseYn(String useYn) {
        this.useYn = useYn;
    }
}