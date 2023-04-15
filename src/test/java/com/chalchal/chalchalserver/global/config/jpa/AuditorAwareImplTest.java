package com.chalchal.chalchalserver.global.config.jpa;

import com.chalchal.chalchalserver.auth.domain.User;
import com.chalchal.chalchalserver.auth.domain.UserRole;
import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.global.fixture.UserDetailsFixture;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class AuditorAwareImplTest {
    @Test
    void getCurrentAuditor_인증된_사용자일_경우() {
        // given
        User user = User.builder()
                .id(9999L)
                .email("email")
                .userId("userId")
                .nickname("nickname")
                .password("password")
                .useYn(Flag.Y)
                .privateYn(Flag.Y)
                .userRole(UserRole.USER)
                .build();

        UserDetails userDetails = UserDetailsFixture.generateAuthUserDetails(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, "", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuditorAware<Long> auditorAware = new AuditorAwareImpl();

        // When
        Optional<Long> currentAuditor = auditorAware.getCurrentAuditor();

        // then
        assertThat(currentAuditor).isNotEmpty();
        assertThat(currentAuditor.get()).isEqualTo(user.getId());
    }

    @Test
    void getCurrentAuditor_인증되지_않은_사용자일_경우() {
        // given
        SecurityContextHolder.getContext().setAuthentication(null);

        AuditorAwareImpl auditorAware = new AuditorAwareImpl();

        // when
        Optional<Long> currentAuditor = auditorAware.getCurrentAuditor();

        // then
        assertThat(currentAuditor).isNotEmpty();
        assertThat(currentAuditor.get()).isEqualTo(0L);
    }
}