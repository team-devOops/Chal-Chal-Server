package com.chalchal.chalchalserver.auth.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UserRoleTest {
    @Test
    void userRoleValues() {
        //given
        String role = "ROLE_USER";

        //when
        String roleValue = UserRole.USER.getValue();

        //then
        assertThat(roleValue).isEqualTo(role);
    }
}