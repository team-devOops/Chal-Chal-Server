package com.chalchal.chalchalserver.todo.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TodoTopicSaveRequestTest {

    @Test
    void todoTopicSaveRequest() {
        //given
        String emoji = "emoji";
        String bgColor = "bgColor";
        Flag useYn = Flag.Y;

        //when
        TodoTopicSaveRequest todoTopicSaveRequest = TodoTopicSaveRequest.builder()
                .emoji(emoji)
                .bgColor(bgColor)
                .useYn(useYn)
                .build();

        //then
        assertThat(todoTopicSaveRequest.getEmoji()).isEqualTo(emoji);
        assertThat(todoTopicSaveRequest.getBgColor()).isEqualTo(bgColor);
        assertThat(todoTopicSaveRequest.getUseYn()).isEqualTo(useYn);
    }
}