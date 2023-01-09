package com.chalchal.chalchalsever;

import com.chalchal.chalchalsever.global.util.StringUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StringUtilsTest {

    @Test
    void 빈문자열_체크() {
        assertThat(StringUtils.isNotEmpty("")).isEqualTo(false);
        assertThat(StringUtils.isNotEmpty(null)).isEqualTo(false);
    }

    @Test
    void 문자열_체크() {
        assertThat(StringUtils.isNotEmpty("zz")).isEqualTo(true);
    }
}
