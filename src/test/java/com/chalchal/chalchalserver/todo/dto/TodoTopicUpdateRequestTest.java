package com.chalchal.chalchalserver.todo.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TodoTopicUpdateRequestTest {

    @Test
    void todoTopicUpdateRequest() {
        //given
        String svcNo = "svcNo";
        String emoji = "emoji";
        String bgColor = "bgColor";
        Flag useYn = Flag.Y;

        //when
        TodoTopicUpdateRequest todoTopicUpdateRequest = TodoTopicUpdateRequest.builder()
                .svcNo(svcNo)
                .emoji(emoji)
                .bgColor(bgColor)
                .useYn(useYn)
                .build();

        //then
        assertThat(todoTopicUpdateRequest.getSvcNo()).isEqualTo(svcNo);
        assertThat(todoTopicUpdateRequest.getEmoji()).isEqualTo(emoji);
        assertThat(todoTopicUpdateRequest.getBgColor()).isEqualTo(bgColor);
        assertThat(todoTopicUpdateRequest.getUseYn()).isEqualTo(useYn);
    }

}