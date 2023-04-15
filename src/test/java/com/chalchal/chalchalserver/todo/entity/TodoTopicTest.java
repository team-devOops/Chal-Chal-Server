package com.chalchal.chalchalserver.todo.entity;

import com.chalchal.chalchalserver.global.dto.Flag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;

class TodoTopicTest {
    @Test
    @DisplayName("TodoTopic 생성자")
    void todoTopicConstructor() {
        TodoTopic todoTopic = new TodoTopic();
        assertThat(todoTopic).isNotNull();
    }

    @Test
    @DisplayName("TodoTopic 생성")
    void todoTopic() {
        String svcNo = "svcNo";
        String emoji = "emoji";
        String bgColor = "bgColor";
        Flag useYn = Flag.Y;
        Long orderSeq = 0L;

        TodoTopic todoTopic = TodoTopic.builder()
                .svcNo(svcNo)
                .emoji(emoji)
                .bgColor(bgColor)
                .useYn(useYn)
                .orderSeq(orderSeq)
                .build();

        assertThat(todoTopic.getSvcNo()).isEqualTo(svcNo);
        assertThat(todoTopic.getEmoji()).isEqualTo(emoji);
        assertThat(todoTopic.getBgColor()).isEqualTo(bgColor);
        assertThat(todoTopic.getUseYn()).isEqualTo(useYn);
        assertThat(todoTopic.getOrderSeq()).isEqualTo(orderSeq);
    }

    @Test
    @DisplayName("emoji 수정")
    void changeEmoji() {
        //given
        TodoTopic todoTopic = create();
        String emoji = "changeEmoji";

        //when
        todoTopic.changeEmoji(emoji);

        //then
        assertThat(todoTopic.getEmoji()).isEqualTo(emoji);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("emoji is null")
    void changeEmojiIsNull(String param) {
        //given
        TodoTopic todoTopic = create();
        String emoji = todoTopic.getEmoji();

        //when
        todoTopic.changeEmoji(param);

        //then
        assertThat(todoTopic.getEmoji()).isEqualTo(emoji);
    }

    @Test
    @DisplayName("bgColor 수정")
    void changeBgColor() {
        //given
        TodoTopic todoTopic = create();
        String bgColor = "bgColor";

        //when
        todoTopic.changeBgColor(bgColor);

        //then
        assertThat(todoTopic.getBgColor()).isEqualTo(bgColor);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("bgColor is null")
    void changeBgColorisNull(String param) {
        //given
        TodoTopic todoTopic = create();
        String bgColor = todoTopic.getBgColor();

        //when
        todoTopic.changeEmoji(param);

        //then
        assertThat(todoTopic.getBgColor()).isEqualTo(bgColor);
    }

    @Test
    @DisplayName("useYn 수정")
    void changeUseYn() {
        //given
        TodoTopic todoTopic = create();
        Flag useYn = Flag.N;

        //when
        todoTopic.changeUseYn(useYn);

        //then
        assertThat(todoTopic.getUseYn()).isEqualTo(useYn);
    }

    @Test
    @DisplayName("useYn is null")
    void changeUseYnIsNull() {
        //given
        TodoTopic todoTopic = create();
        Flag useYn = todoTopic.getUseYn();

        //when
        todoTopic.changeUseYn(null);

        //then
        assertThat(todoTopic.getUseYn()).isEqualTo(useYn);
    }

    private TodoTopic create() {
        return TodoTopic.builder()
                .svcNo("svcNo")
                .emoji("emoji")
                .bgColor("bgColor")
                .useYn(Flag.Y)
                .orderSeq(0L)
                .build();
    }
}