package com.chalchal.chalchalserver.utils;

import com.chalchal.chalchalserver.global.util.RandomUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RandomUtilsTest {

    @Test
    void 랜덤값_자릿수_성공() {
        //given
        String random = "";

        //when
        random = RandomUtils.getRandomIntByLength(6);

        //then
        assertThat(random.length()).isEqualTo(6);
        System.out.println(random);
    }
}
