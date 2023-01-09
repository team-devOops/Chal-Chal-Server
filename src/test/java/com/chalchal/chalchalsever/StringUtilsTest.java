package com.chalchal.chalchalsever;

import com.chalchal.chalchalsever.global.util.StringUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StringUtilsTest {

    @Test
    void 빈문자열_isNotEmpty_체크() {
        String str1 = "";
        String str2 = null;

        boolean result1 = StringUtils.isNotEmpty(str1);
        boolean result2 = StringUtils.isNotEmpty(str2);

        assertThat(result1).isEqualTo(false);
        assertThat(result2).isEqualTo(false);
    }

    @Test
    void 문자열_isNotEmpty_체크() {
        assertThat(StringUtils.isNotEmpty("zz")).isEqualTo(true);
    }

    @Test
    void 빈문자열_isEmpty_체크() {
        String str1 = "";
        String str2 = null;

        boolean result1 = StringUtils.isEmpty(str1);
        boolean result2 = StringUtils.isEmpty(str2);

        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
    }

    @Test
    void 문자열_isEmpty_체크() {
        assertThat(StringUtils.isNotEmpty("zz")).isFalse();
    }
}
