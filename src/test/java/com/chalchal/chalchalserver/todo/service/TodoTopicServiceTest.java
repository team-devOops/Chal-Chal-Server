package com.chalchal.chalchalserver.todo.service;

import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.global.exception.BaseException;
import com.chalchal.chalchalserver.todo.dto.TodoTopicSaveRequest;
import com.chalchal.chalchalserver.todo.dto.TodoTopicUpdateRequest;
import com.chalchal.chalchalserver.todo.entity.TodoTopic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TodoTopicServiceTest {

    @Autowired
    private TodoTopicService todoTopicService;

    @Test
    @DisplayName("TodoTopic 생성")
    void createTodoTopic() {
        //given
        String emoji = "emoji";
        String bgColor = "bgColor";
        Flag useYn = Flag.Y;

        TodoTopicSaveRequest todoTopicSaveRequest = TodoTopicSaveRequest.builder()
                .emoji(emoji)
                .bgColor(bgColor)
                .useYn(useYn)
                .build();

        //when
        TodoTopic todoTopic = todoTopicService.createTodoTopic(todoTopicSaveRequest);

        //then
        assertSoftly(softAssertions -> {
            assertThat(todoTopic.getEmoji()).isEqualTo(emoji);
            assertThat(todoTopic.getBgColor()).isEqualTo(bgColor);
            assertThat(todoTopic.getUseYn()).isEqualTo(useYn);
        });
    }

    @Test
    @DisplayName("TodoTopic 성공")
    void updateTodoTopic() {
        //given
        TodoTopic todoTopic = create();
        String svcNo = todoTopic.getSvcNo();
        String emoji = "changeEmoji";

        TodoTopicUpdateRequest todoTopicUpdateRequest = TodoTopicUpdateRequest.builder()
                .svcNo(svcNo)
                .emoji(emoji)
                .build();

        //when
        TodoTopic result = todoTopicService.updateTodoTopic(todoTopicUpdateRequest);

        //then
        assertSoftly(softAssertions -> {
            assertThat(result.getSvcNo()).isEqualTo(svcNo);
            assertThat(result.getEmoji()).isEqualTo(emoji);
            assertThat(result.getBgColor()).isEqualTo(todoTopic.getBgColor());
            assertThat(result.getUseYn()).isEqualTo(todoTopic.getUseYn());
        });
    }

    @Test
    @DisplayName("TodoTopic 잘못된 svcNo 수정")
    void updateTodoTopicException() {
        //given
        String svcNo = "잘못된_svcNo";
        TodoTopicUpdateRequest todoTopicUpdateRequest = TodoTopicUpdateRequest.builder()
                .svcNo(svcNo)
                .build();

        //when & then
        assertThrows(BaseException.class, () -> todoTopicService.updateTodoTopic(todoTopicUpdateRequest));
    }

    @Test
    @DisplayName("TodoTopic 조회")
    void findTodoTopicBySvcNo() {
        //given
        TodoTopic todoTopic = create();
        String svcNo = todoTopic.getSvcNo();

        //when
        TodoTopic result = todoTopicService.findTodoTopicBySvcNo(svcNo);

        //then
        assertSoftly(softAssertions -> {
            assertThat(result.getSvcNo()).isEqualTo(svcNo);
            assertThat(result.getEmoji()).isEqualTo(todoTopic.getEmoji());
            assertThat(result.getBgColor()).isEqualTo(todoTopic.getBgColor());
            assertThat(result.getUseYn()).isEqualTo(todoTopic.getUseYn());
            assertThat(result.getOrderSeq()).isEqualTo(todoTopic.getOrderSeq());
        });
    }

    @Test
    @DisplayName("TodoTopic 잘못된 svcNo 조회")
    void findTodoTopicBySvcNoException() {
        //given
        String svcNo = "잘못된_svcNo";

        //when & then
        assertThrows(BaseException.class, () -> todoTopicService.findTodoTopicBySvcNo(svcNo));
    }

    @Test
    @DisplayName("TodoTopic 삭제")
    void deleteTodoTopic() {
        //given
        TodoTopic todoTopic = create();
        String svcNo = todoTopic.getSvcNo();

        //when
        TodoTopic result = todoTopicService.deleteTodoTopic(svcNo);

        //then
        assertThat(result.getUseYn()).isEqualTo(Flag.N);
    }

    @Test
    @DisplayName("TodoTopic 잘못된 svcNo 삭제")
    void deleteTodoTopicException() {
        //given
        String svcNo = "잘못된_svcNo";

        //when & then
        assertThrows(BaseException.class, () -> todoTopicService.deleteTodoTopic(svcNo));
    }

    private TodoTopic create() {
        TodoTopicSaveRequest todoTopicSaveRequest = TodoTopicSaveRequest.builder()
                .emoji("emoji")
                .bgColor("bgColor")
                .useYn(Flag.Y)
                .build();

        return todoTopicService.createTodoTopic(todoTopicSaveRequest);
    }
}