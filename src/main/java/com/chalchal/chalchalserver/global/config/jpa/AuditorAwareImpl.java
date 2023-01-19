package com.chalchal.chalchalserver.global.config.jpa;

import com.chalchal.chalchalserver.auth.domain.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.of(0L);
        }

        return Optional.of(((User)authentication.getPrincipal()).getId());
    }
}
