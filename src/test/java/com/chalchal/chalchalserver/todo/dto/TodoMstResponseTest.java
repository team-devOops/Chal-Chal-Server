package com.chalchal.chalchalserver.todo.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.todo.entity.TodoMst;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TodoMstResponseTest {

    @Test
    void TodoMstSaveResponse() {
        String svcNo = "svcNo";
        String topicKey = "topicKey";
        String title = "title";
        String memo = "memo";
        Flag useYn = Flag.Y;
        Flag successYn = Flag.N;

        TodoMstResponse response = TodoMstResponse.builder()
                .svcNo(svcNo)
                .topicKey(topicKey)
                .title(title)
                .memo(memo)
                .useYn(useYn)
                .successYn(successYn)
                .build();

        assertThat(response.getSvcNo()).isEqualTo(svcNo);
        assertThat(response.getTopicKey()).isEqualTo(topicKey);
        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getMemo()).isEqualTo(memo);
        assertThat(response.getUseYn()).isEqualTo(useYn);
        assertThat(response.getSuccessYn()).isEqualTo(successYn);
    }

    @Test
    void from() {
        TodoMst todoMst = TodoMst.builder()
                .svcNo("svcNo")
                .reSvcNo(null)
                .topicKey("topicKey")
                .orderSeq(0L)
                .title("title")
                .memo(null)
                .useYn(Flag.Y)
                .successYn(Flag.N)
                .successDate(null)
                .build();

        TodoMstResponse todoMstResponse = TodoMstResponse.from(todoMst);

        assertThat(todoMstResponse.getSvcNo()).isEqualTo(todoMst.getSvcNo());
        assertThat(todoMstResponse.getTopicKey()).isEqualTo(todoMst.getTopicKey());
        assertThat(todoMstResponse.getTitle()).isEqualTo(todoMst.getTitle());
        assertThat(todoMstResponse.getMemo()).isEqualTo(todoMst.getMemo());
        assertThat(todoMstResponse.getUseYn()).isEqualTo(todoMst.getUseYn());
        assertThat(todoMstResponse.getSuccessYn()).isEqualTo(todoMst.getSuccessYn());
    }
}