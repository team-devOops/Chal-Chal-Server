package com.chalchal.chalchalserver.todo.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TodoMstSaveRequestTest {

    @Test
    void TodoMstSaveRequest() {
        //given
        String topicKey = "topicKey";
        String title = "title";
        String memo = "memo";

        //when
        TodoMstSaveRequest todoMstSaveRequest = TodoMstSaveRequest.builder()
                .topicKey(topicKey)
                .title(title)
                .memo(memo)
                .build();

        //then
        assertThat(todoMstSaveRequest.getTopicKey()).isEqualTo(topicKey);
        assertThat(todoMstSaveRequest.getTitle()).isEqualTo(title);
        assertThat(todoMstSaveRequest.getMemo()).isEqualTo(memo);
    }
}