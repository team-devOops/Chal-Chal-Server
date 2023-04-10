package com.chalchal.chalchalserver.todo.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.todo.entity.TodoTopic;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.*;

class TodoTopicResponseTest {
    @Test
    @DisplayName("todoTopic 생성자 생성")
    void todoTopicConstructor() {
        //given
        String svcNo = "svcNo";
        String emoji = "emoji";
        String bgColor = "bgColor";
        Flag useYn = Flag.Y;

        //when
        TodoTopicResponse response = TodoTopicResponse.builder()
                .svcNo(svcNo)
                .emoji(emoji)
                .bgColor(bgColor)
                .useYn(useYn)
                .build();

        //then
        assertSoftly(softAssertions -> {
            Assertions.assertThat(response.getSvcNo()).isEqualTo(svcNo);
            Assertions.assertThat(response.getEmoji()).isEqualTo(emoji);
            Assertions.assertThat(response.getBgColor()).isEqualTo(bgColor);
            Assertions.assertThat(response.getUseYn()).isEqualTo(useYn);
        });
    }

    @Test
    @DisplayName("todoTopic from")
    void from() {
        //given
        String svcNo = "svcNo";
        String emoji = "emoji";
        String bgColor = "bgColor";
        Flag useYn = Flag.Y;

        TodoTopic todoTopic = TodoTopic.builder()
                .svcNo(svcNo)
                .emoji(emoji)
                .bgColor(bgColor)
                .useYn(useYn)
                .build();

        //when
        TodoTopicResponse response = TodoTopicResponse.from(todoTopic);

        assertSoftly(softAssertions -> {
            Assertions.assertThat(response.getSvcNo()).isEqualTo(svcNo);
            Assertions.assertThat(response.getEmoji()).isEqualTo(emoji);
            Assertions.assertThat(response.getBgColor()).isEqualTo(bgColor);
            Assertions.assertThat(response.getUseYn()).isEqualTo(useYn);
        });
    }
}