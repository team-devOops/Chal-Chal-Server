package com.chalchal.chalchalserver.global;

import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.todo.dto.TodoMstSaveRequest;
import com.chalchal.chalchalserver.todo.entity.TodoMst;
import com.chalchal.chalchalserver.todo.service.TodoMstService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BaseDomainTest {
    @Autowired
    private TodoMstService todoMstService;

    @Test
    void baseDomain() {
        //given
        TodoMstSaveRequest todoMstSaveRequest = TodoMstSaveRequest.builder()
                .title("title")
                .memo("memo")
                .build();

        //when
        TodoMst result = todoMstService.createTodoMst(todoMstSaveRequest);

        //then
        assertSoftly(softAssertions -> {
            assertThat(result.getRegId()).isNotNull();
            assertThat(result.getRegDate()).isNotNull();
            assertThat(result.getUpdId()).isNotNull();
            assertThat(result.getUpdDate()).isNotNull();
        });
    }
}