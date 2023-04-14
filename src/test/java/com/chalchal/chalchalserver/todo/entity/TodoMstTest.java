package com.chalchal.chalchalserver.todo.entity;

import com.chalchal.chalchalserver.global.dto.Flag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class TodoMstTest {

    @Test
    @DisplayName("TodoMst 생성자")
    void todoMstConstructor() {
        TodoMst todoMst = new TodoMst();
        assertThat(todoMst).isNotNull();
    }

    @Test
    @DisplayName("TodoMst 생성")
    void todoMst() {
        String svcNo = "svcNo";
        String reSvcNo = null;
        String topicKey = null;
        long orderSeq = 0L;
        String title = "title";
        String memo = "memo";
        Flag useYn = Flag.Y;
        Flag successYn = Flag.N;
        LocalDateTime successDate = null;

        TodoMst todoMst = TodoMst.builder()
                .svcNo(svcNo)
                .reSvcNo(reSvcNo)
                .topicKey(topicKey)
                .orderSeq(orderSeq)
                .title(title)
                .memo(memo)
                .useYn(useYn)
                .successYn(successYn)
                .successDate(successDate)
                .build();

        assertThat(todoMst.getSvcNo()).isEqualTo(svcNo);
        assertThat(todoMst.getReSvcNo()).isEqualTo(reSvcNo);
        assertThat(todoMst.getTopicKey()).isEqualTo(topicKey);
        assertThat(todoMst.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(todoMst.getTitle()).isEqualTo(title);
        assertThat(todoMst.getMemo()).isEqualTo(memo);
        assertThat(todoMst.getUseYn()).isEqualTo(useYn);
        assertThat(todoMst.getSuccessYn()).isEqualTo(successYn);
        assertThat(todoMst.getSuccessDate()).isEqualTo(successDate);
    }

    @Test
    @DisplayName("useYn 변경")
    void changeUseYn() {
        //given
        TodoMst todoMst = createTodoMst();
        Flag useYn = Flag.N;

        //when
        todoMst.changeUseYn(useYn);

        //then
        assertThat(todoMst.getUseYn()).isEqualTo(useYn);
    }

    @Test
    @DisplayName("useYn is null")
    void changeUseYnIsNull() {
        //given
        TodoMst todoMst = createTodoMst();
        Flag useYn = todoMst.getUseYn();

        //when
        todoMst.changeUseYn(null);

        //then
        assertThat(todoMst.getUseYn()).isEqualTo(useYn);
    }

    @Test
    @DisplayName("memo 변경")
    void changeMemo() {
        //given
        TodoMst todoMst = createTodoMst();
        String memo = "changeMemo";

        //when
        todoMst.changeMemo(memo);

        //then
        assertThat(todoMst.getMemo()).isEqualTo(memo);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("memo is null")
    void changeMemoIsNull(String paramMemo) {
        //given
        TodoMst todoMst = createTodoMst();
        String memo = todoMst.getMemo();

        //when
        todoMst.changeMemo(paramMemo);

        //then
        assertThat(todoMst.getMemo()).isEqualTo(memo);
    }

    @Test
    @DisplayName("title 변경")
    void changeTitle() {
        //given
        TodoMst todoMst = createTodoMst();
        String title = "changeTitle";

        //when
        todoMst.changeTitle(title);

        //then
        assertThat(todoMst.getTitle()).isEqualTo(title);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("title is null")
    void changeTitleIsNull(String paramTitle) {
        //given
        TodoMst todoMst = createTodoMst();
        String title = todoMst.getTitle();

        //when
        todoMst.changeMemo(paramTitle);

        //then
        assertThat(todoMst.getTitle()).isEqualTo(title);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"changeTopicKey"})
    @DisplayName("topicKey 변경")
    void changeTopicKey(String topicKey) {
        //given
        TodoMst todoMst = createTodoMst();

        //when
        todoMst.changeTopicKey(topicKey);

        //then
        assertThat(todoMst.getTopicKey()).isEqualTo(topicKey);
    }

    @Test
    @DisplayName("successYn 변경")
    void changeSuccessYn() {
        //given
        TodoMst todoMst = createTodoMst();
        LocalDateTime successDate = todoMst.getSuccessDate();
        Flag successYn = Flag.Y;

        //when
        todoMst.changeSuccessYn(Flag.Y);

        //then
        assertThat(todoMst.getSuccessYn()).isEqualTo(successYn);
        assertThat(successDate).isNotEqualTo(todoMst.getSuccessDate());
        assertThat(todoMst.getSuccessDate()).isNotNull();
    }

    @Test
    @DisplayName("successDate 변경")
    void changeSuccessDate() {
        //given
        TodoMst todoMst = createTodoMst();
        LocalDateTime successDate = LocalDateTime.now();

        //when
        todoMst.updateSuccessDate(successDate);

        //then
        assertThat(todoMst.getSuccessDate()).isEqualTo(successDate);
    }

    private TodoMst createTodoMst() {
        return TodoMst.builder()
                .svcNo("XXX")
                .reSvcNo(null)
                .topicKey(null)
                .memo("MEMO")
                .title("TITLE")
                .useYn(Flag.Y)
                .successYn(Flag.N)
                .successDate(null)
                .orderSeq(0L)
                .build();
    }
}