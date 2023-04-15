package com.chalchal.chalchalserver.global.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FlagTest {

    @Test
    void flagValues() {
        //given
        String expectedYValue = "Y";
        String expectedNValue = "N";

        //when
        String actualYValue = Flag.Y.getValue();
        String actualNValue = Flag.N.getValue();

        //then
        assertThat(actualYValue).isEqualTo(expectedYValue);
        assertThat(actualNValue).isEqualTo(expectedNValue);
    }

    @Test
    public void flagEnumValues() {
        //given
        String yValue = "Y";
        String nValue = "N";

        //when
        Flag yFlag = Flag.valueOf(yValue);
        Flag nFlag = Flag.valueOf(nValue);

        //then
        assertThat(yFlag).isEqualTo(Flag.Y);
        assertThat(nFlag).isEqualTo(Flag.N);
    }
}