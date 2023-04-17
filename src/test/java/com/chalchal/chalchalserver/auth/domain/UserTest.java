package com.chalchal.chalchalserver.auth.domain;

import com.chalchal.chalchalserver.global.dto.Flag;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.*;

class UserTest {

    @Test
    void userNoArgsConstructor() {
        User user = new User();
        assertThat(user).isNotNull();
    }

    @Test
    void user() {
        Long id = 99L;
        String email = "email";
        String userId = "userId";
        String nickname = "nickname";
        String password = "password";
        Flag useYn = Flag.Y;
        String intro = "intro";
        Flag privateYn = Flag.Y;
        UserRole userRole = null;

        User user = User.builder()
                .id(id)
                .email(email)
                .userId(userId)
                .nickname(nickname)
                .password(password)
                .useYn(useYn)
                .intro(intro)
                .privateYn(privateYn)
                .userRole(userRole)
                .build();

        assertSoftly(softAssertions -> {
            assertThat(user.getId()).isEqualTo(id);
            assertThat(user.getEmail()).isEqualTo(email);
            assertThat(user.getUserId()).isEqualTo(userId);
            assertThat(user.getNickname()).isEqualTo(nickname);
            assertThat(user.getPassword()).isEqualTo(password);
            assertThat(user.getUseYn()).isEqualTo(useYn);
            assertThat(user.getIntro()).isEqualTo(intro);
            assertThat(user.getPrivateYn()).isEqualTo(privateYn);
            assertThat(user.getUserRole()).isEqualTo(userRole);
        });
    }

    @Test
    void getAuthorities() {
        //given
        String email = "email";
        String password = "password";

        User user = create(User.builder()
                .email(email)
                .password(password)
                .build());

        //when
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getUserRole().getValue()));

        //then
        assertThat(Arrays.stream(user.getAuthorities().toArray())
                .allMatch(authority -> ((GrantedAuthority) authority).getAuthority().equals("ROLE_GUEST"))).isTrue();
    }

    @Test
    void getPassword() {
        //given
        String email = "email";
        String password = "password";

        User user = create(User.builder()
                .email(email)
                .password(password)
                .build());

        //then
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    void getUsername() {
        //given
        String email = "email";
        String password = "password";

        User user = create(User.builder()
                .email(email)
                .password(password)
                .build());

        //then
        assertThat(user.getUsername()).isEqualTo(email);
    }

    @Test
    void isAccountNonExpired() {
        //given
        String email = "email";
        String password = "password";

        User user = create(User.builder()
                .email(email)
                .password(password)
                .build());

        //then
        assertThat(user.isAccountNonExpired()).isTrue();
    }

    @Test
    void isAccountNonLocked() {
        //given
        String email = "email";
        String password = "password";

        User user = create(User.builder()
                .email(email)
                .password(password)
                .build());

        //then
        assertThat(user.isAccountNonLocked()).isTrue();
    }

    @Test
    void isCredentialsNonExpired() {
        //given
        String email = "email";
        String password = "password";

        User user = create(User.builder()
                .email(email)
                .password(password)
                .build());

        //then
        assertThat(user.isCredentialsNonExpired()).isTrue();
    }

    @Test
    void isEnabled() {
        //given
        String email = "email";
        String password = "password";

        User user = create(User.builder()
                .email(email)
                .password(password)
                .build());

        //then
        assertThat(user.isEnabled()).isTrue();
    }

    @Test
    void changeUseYn() {
        //given
        String email = "email";
        String password = "password";

        User user = create(User.builder()
                .email(email)
                .password(password)
                .build());

        //when
        user.changeUseYn(Flag.N);

        //then
        assertThat(user.getUseYn()).isEqualTo(Flag.N);
    }

    @Test
    void encodePassword() {
        //given
        String email = "email";
        String password = "password";

        User user = create(User.builder()
                .email(email)
                .password(password)
                .build());

        //when
        user.encodePassword(password);

        //then
        assertThat(user.getPassword()).isNotEqualTo(password);
    }

    private User create(User param) {
        return User.builder()
                .id(99L)
                .email(param.getEmail())
                .userId("userId")
                .nickname("nickname")
                .password(param.getPassword())
                .useYn(Flag.Y)
                .intro("intro")
                .privateYn(Flag.Y)
                .userRole(UserRole.GUEST)
                .build();
    }
}