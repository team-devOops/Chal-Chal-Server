package com.chalchal.chalchalserver.global.fixture;

import com.chalchal.chalchalserver.auth.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsFixture implements UserDetails {
    private final User user;

    private UserDetailsFixture(User user) {
        this.user = user;
    }

    public static UserDetailsFixture generateAuthUserDetails(User user) {
        UserDetailsFixture userDetailsFixture = new UserDetailsFixture(user);
        return userDetailsFixture;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
