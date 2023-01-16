package com.chalchal.chalchalserver.utils;

import com.chalchal.chalchalserver.global.util.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StringUtilsTest {

    @ParameterizedTest
    @NullAndEmptySource
    void 빈문자열_isNotEmpty_체크(String input) {
        boolean result = StringUtils.isNotEmpty(input);

        assertThat(result).isEqualTo(false);
    }

    @ParameterizedTest
    @ValueSource(strings = { "zz" })
    void 문자열_isNotEmpty_체크(String input) {
        assertThat(StringUtils.isNotEmpty(input)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 빈문자열_isEmpty_체크(String input) {
        boolean value = StringUtils.isEmpty(input);
        assertThat(value).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = { "zz" })
    void 문자열_isEmpty_체크(String input) {
        assertThat(StringUtils.isEmpty(input)).isFalse();
    }
}
