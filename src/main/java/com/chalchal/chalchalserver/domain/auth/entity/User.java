package com.chalchal.chalchalserver.domain.auth.entity;
import com.chalchal.chalchalserver.domain.BaseDomain;
import com.chalchal.chalchalserver.global.dto.Flag;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("유저 KEY")
    @Column(name = "ID", nullable = false, columnDefinition = "bigint")
    private Long id;

    @Comment("이메일")
    @Column(unique = true, nullable = false, columnDefinition = "varchar(128)")
    private String email;

    @Comment("유저 ID")
    @Column(name = "USER_ID", nullable = false, columnDefinition = "varchar(16)")
    private String userId;

    @Comment("닉네임")
    @Column(name = "NICKNAME", columnDefinition = "varchar(32)")
    private String nickname;

    @Comment("비밀번호")
    @Column(name = "PASSWORD", nullable = false, columnDefinition = "varchar(64)")
    private String password;

    @Comment("사용여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "USE_YN", nullable = false, columnDefinition = "char(1)")
    private Flag useYn;

    @Comment("자기소개")
    @Column(name = "INTRO", columnDefinition = "varchar(512)")
    private String intro;

    @Comment("사용여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "PRIVATE_YN", columnDefinition = "char(1)")
    private Flag privateYn;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        //roles.add(new SimpleGrantedAuthority(userRole.getValue()));
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

    public void changeUseYn(Flag useYn) {
        this.useYn = useYn;
    }
}