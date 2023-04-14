package com.chalchal.chalchalserver.todo.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.todo.entity.TodoMst;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TodoMstSaveResponseTest {

    @Test
    void TodoMstSaveResponse() {
        String svcNo = "svcNo";
        String topicKey = "topicKey";
        String title = "title";
        String memo = "memo";
        Flag useYn = Flag.Y;
        Flag successYn = Flag.N;

        TodoMstSaveResponse todoMstSaveResponse = TodoMstSaveResponse.builder()
                .svcNo(svcNo)
                .topicKey(topicKey)
                .title(title)
                .memo(memo)
                .useYn(useYn)
                .successYn(successYn)
                .build();

        assertThat(todoMstSaveResponse.getSvcNo()).isEqualTo(svcNo);
        assertThat(todoMstSaveResponse.getTopicKey()).isEqualTo(topicKey);
        assertThat(todoMstSaveResponse.getTitle()).isEqualTo(title);
        assertThat(todoMstSaveResponse.getMemo()).isEqualTo(memo);
        assertThat(todoMstSaveResponse.getUseYn()).isEqualTo(useYn);
        assertThat(todoMstSaveResponse.getSuccessYn()).isEqualTo(successYn);
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

        TodoMstSaveResponse todoMstSaveResponse = TodoMstSaveResponse.from(todoMst);

        assertThat(todoMstSaveResponse.getSvcNo()).isEqualTo(todoMst.getSvcNo());
        assertThat(todoMstSaveResponse.getTopicKey()).isEqualTo(todoMst.getTopicKey());
        assertThat(todoMstSaveResponse.getTitle()).isEqualTo(todoMst.getTitle());
        assertThat(todoMstSaveResponse.getMemo()).isEqualTo(todoMst.getMemo());
        assertThat(todoMstSaveResponse.getUseYn()).isEqualTo(todoMst.getUseYn());
        assertThat(todoMstSaveResponse.getSuccessYn()).isEqualTo(todoMst.getSuccessYn());
    }
}