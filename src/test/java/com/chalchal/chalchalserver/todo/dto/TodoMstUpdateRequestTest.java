package com.chalchal.chalchalserver.todo.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TodoMstUpdateRequestTest {

    @Test
    void TodoMstUpdateRequest() {
        //given
        String svcNo = "svcNo";
        String topicKey = null;
        String title = "title";
        String memo = "memo";
        Flag useYn = Flag.Y;
        Flag successYn = Flag.N;

        //when
        TodoMstUpdateRequest todoMstUpdateRequest = TodoMstUpdateRequest.builder()
                .svcNo(svcNo)
                .topicKey(topicKey)
                .title(title)
                .memo(memo)
                .useYn(useYn)
                .successYn(successYn)
                .build();

        //then
        assertThat(todoMstUpdateRequest.getSvcNo()).isEqualTo(svcNo);
        assertThat(todoMstUpdateRequest.getTopicKey()).isEqualTo(topicKey);
        assertThat(todoMstUpdateRequest.getTitle()).isEqualTo(title);
        assertThat(todoMstUpdateRequest.getMemo()).isEqualTo(memo);
        assertThat(todoMstUpdateRequest.getUseYn()).isEqualTo(useYn);
        assertThat(todoMstUpdateRequest.getSuccessYn()).isEqualTo(successYn);
    }
}