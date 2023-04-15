package com.chalchal.chalchalserver.global.generate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SvcNoTest {
    private String svcNoType;
    private SvcNo svcNo;

    @BeforeEach
    void init() {
        svcNoType = "TEST";
        svcNo = new SvcNo(svcNoType);
    }

    @Test
    void svcNo() {
        assertThat(SvcNo.getSvcNoType()).isEqualTo(svcNoType);
    }

    @Test
    void getSvcNo() {
        String param = SvcNo.getSvcNo();

        assertThat(param.length()).isEqualTo(SvcNo.SVC_NO_MAX_SIZE);
    }
}