package com.chalchal.chalchalsever.domain.auth.entity;
import com.chalchal.chalchalsever.domain.BaseDomain;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@Table(name = "T_USER")
@NoArgsConstructor
public class User extends BaseDomain implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("유저 KEY")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Comment("이메일")
    @Column(unique = true)
    private String email;

    @Comment("유저 ID")
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Comment("닉네임")
    @Column(name = "NICKNAME")
    private String nickname;

    @Comment("비밀번호")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Comment("사용여부")
    @Column(name = "USE_YN", nullable = false)
    private String useYn;

    @Comment("자기소개")
    @Column(name = "INTRO")
    private String intro;

    @Column(name = "PRIVATE_YN")
    @Comment("사용여부")
    private String privateYn;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

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