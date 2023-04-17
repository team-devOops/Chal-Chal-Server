package com.chalchal.chalchalserver.todo.service;

import com.chalchal.chalchalserver.auth.domain.User;
import com.chalchal.chalchalserver.auth.domain.UserRole;
import com.chalchal.chalchalserver.global.config.jpa.AuditorAwareImpl;
import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.global.exception.BaseException;
import com.chalchal.chalchalserver.global.fixture.UserDetailsFixture;
import com.chalchal.chalchalserver.todo.dto.TodoMstSaveRequest;
import com.chalchal.chalchalserver.todo.dto.TodoMstUpdateRequest;
import com.chalchal.chalchalserver.todo.entity.TodoMst;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TodoMstServiceTest {
    @Autowired
    private TodoMstService todoMstService;

    @Test
    void todoMst_생성() {
        //given
        TodoMstSaveRequest todoMstSaveRequest = TodoMstSaveRequest.builder()
                .topicKey("topicKey")
                .title("title")
                .memo("memo")
                .build();

        //when
        TodoMst result = todoMstService.createTodoMst(todoMstSaveRequest);

        //then
        assertSoftly(softAssertions -> {
            assertThat(result.getTopicKey()).isEqualTo(todoMstSaveRequest.getTopicKey());
            assertThat(result.getTitle()).isEqualTo(todoMstSaveRequest.getTitle());
            assertThat(result.getMemo()).isEqualTo(todoMstSaveRequest.getMemo());
        });
    }

    @Test
    void todoMst_조회() {
        //given
        TodoMst todoMst = create();

        //when
        TodoMst result = todoMstService.findTodoMstBySvcNo(todoMst.getSvcNo());

        //then
        assertSoftly(softAssertions -> {
            assertThat(result.getSvcNo()).isEqualTo(todoMst.getSvcNo());
            assertThat(result.getReSvcNo()).isEqualTo(todoMst.getReSvcNo());
            assertThat(result.getTopicKey()).isEqualTo(todoMst.getTopicKey());
            assertThat(result.getOrderSeq()).isEqualTo(todoMst.getOrderSeq());
            assertThat(result.getTitle()).isEqualTo(todoMst.getTitle());
            assertThat(result.getMemo()).isEqualTo(todoMst.getMemo());
            assertThat(result.getUseYn()).isEqualTo(todoMst.getUseYn());
            assertThat(result.getSuccessYn()).isEqualTo(todoMst.getSuccessYn());
            assertThat(result.getSuccessDate()).isEqualTo(todoMst.getSuccessDate());
        });
    }

    @Test
    void todoMst_잘못된_svcNo_조회() {
        //given
        String svcNo = "잘못된_svcNo";

        //when & then
        assertThrows(BaseException.class, () -> todoMstService.findTodoMstBySvcNo(svcNo));
    }

    @Test
    void todoMst_수정() {
        //given
        TodoMst todoMst = create();
        TodoMstUpdateRequest todoMstUpdateRequest = TodoMstUpdateRequest.builder()
                .svcNo(todoMst.getSvcNo())
                .topicKey("updateTopicKey")
                .title("updateTitle")
                .memo("updateMemo")
                .build();

        //when
        TodoMst result = todoMstService.updateTodoMst(todoMstUpdateRequest);

        //then
        assertSoftly(softAssertions -> {
            assertThat(result.getSvcNo()).isEqualTo(todoMstUpdateRequest.getSvcNo());
            assertThat(result.getTopicKey()).isEqualTo(todoMstUpdateRequest.getTopicKey());
            assertThat(result.getTitle()).isEqualTo(todoMstUpdateRequest.getTitle());
            assertThat(result.getMemo()).isEqualTo(todoMstUpdateRequest.getMemo());
            assertThat(result.getUseYn()).isEqualTo(todoMst.getUseYn());
            assertThat(result.getSuccessYn()).isEqualTo(todoMst.getSuccessYn());
        });
    }

    @Test
    void todoMst_잘못된_svcNo_수정() {
        //given
        TodoMstUpdateRequest todoMstUpdateRequest = TodoMstUpdateRequest.builder()
                .svcNo("잘못된_svcNo")
                .topicKey("updateTopicKey")
                .title("updateTitle")
                .memo("updateMemo")
                .build();

        //when & then
        assertThrows(BaseException.class, () -> todoMstService.updateTodoMst(todoMstUpdateRequest));
    }

    @Test
    void todoMst_삭제() {
        //given
        TodoMst todoMst = create();
        String svcNo = todoMst.getSvcNo();

        //when
        TodoMst result = todoMstService.deleteTodoMst(svcNo);

        //then
        assertThat(result.getUseYn()).isEqualTo(Flag.N);
    }

    @Test
    void todoMst_잘못된_svcNo_삭제() {
        //given
        String svcNo = "잘못된_svcNo";

        //when & then
        assertThrows(BaseException.class, () -> todoMstService.deleteTodoMst(svcNo));
    }

    @Test
    void findTodoMstByRegId() {
        User user = User.builder()
                .id(888L)
                .email("email")
                .userId("userId")
                .nickname("nickname")
                .password("password")
                .useYn(Flag.Y)
                .privateYn(Flag.Y)
                .userRole(UserRole.USER)
                .build();

        UserDetails userDetails = UserDetailsFixture.generateAuthUserDetails(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, "", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuditorAware<Long> auditorAware = new AuditorAwareImpl();

        for (int i = 0; i < 3; i++) {
            create();
        }

        Optional<Long> currentAuditor = auditorAware.getCurrentAuditor();
        List<TodoMst> todoMstList = todoMstService.findTodoMstByRegId(currentAuditor.get());

        assertThat(todoMstList.size()).isEqualTo(3);
    }

    private TodoMst create() {
        return todoMstService.createTodoMst(TodoMstSaveRequest.builder()
                .topicKey("topicKey")
                .title("title")
                .memo("memo")
                .build());
    }
}