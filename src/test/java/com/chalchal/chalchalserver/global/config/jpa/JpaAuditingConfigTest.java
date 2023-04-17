package com.chalchal.chalchalserver.global.config.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class JpaAuditingConfigTest {
    @Test
    void testAuditorProvider() {
        // given
        AuditorAware<Long> auditorProvider = new JpaAuditingConfig().auditorProvider();

        // when
        Optional<Long> optionalAuditor = auditorProvider.getCurrentAuditor();

        // then
        assertThat(optionalAuditor).isNotEmpty();
        assertThat(optionalAuditor.isPresent()).isTrue();
        assertThat(optionalAuditor.get().longValue()).isEqualTo(0L);
    }

}