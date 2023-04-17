package com.chalchal.chalchalserver.todo.controller;

import com.chalchal.chalchalserver.auth.domain.User;
import com.chalchal.chalchalserver.auth.domain.UserRole;
import com.chalchal.chalchalserver.global.config.jpa.AuditorAwareImpl;
import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.global.fixture.UserDetailsFixture;
import com.chalchal.chalchalserver.todo.dto.*;
import com.chalchal.chalchalserver.todo.entity.TodoMst;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TodoMstControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    String baseUrl;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        baseUrl = "/todo";

        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("할 일을 등록합니다.")
    void post() throws Exception {
        //given
        String topicKey = "topicKey";
        String title = "title";
        String memo = "memo";

        TodoMstSaveRequest request = TodoMstSaveRequest.builder()
                .topicKey(topicKey)
                .title(title)
                .memo(memo)
                .build();

        //when
        TodoMstResponse result = 할일을_등록함(request);

        //then
        assertSoftly(softAssertions -> {
            assertThat(result.getTopicKey()).isEqualTo(topicKey);
            assertThat(result.getTitle()).isEqualTo(title);
            assertThat(result.getMemo()).isEqualTo(memo);
        });
    }

    @Test
    @DisplayName("할 일을 단건 조회합니다.")
    void get() throws Exception {
        //given
        String topicKey = "topicKey";
        String title = "title";
        String memo = "memo";

        TodoMstSaveRequest request = TodoMstSaveRequest.builder()
                .topicKey(topicKey)
                .title(title)
                .memo(memo)
                .build();

        TodoMstResponse result = 할일을_등록함(request);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/" + result.getSvcNo()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        assertSoftly(softAssertions -> {
            assertThat(result.getTopicKey()).isEqualTo(topicKey);
            assertThat(result.getTitle()).isEqualTo(title);
            assertThat(result.getMemo()).isEqualTo(memo);
        });
    }

    @Test
    @DisplayName("특정 회원의 할일을 조회합니다.")
    void getList() throws Exception {
        //given
        final int requestTry = 3;

        User user = User.builder()
                .id(1111L)
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

        String topicKey = "topicKey";
        String title = "title";
        String memo = "memo";

        TodoMstSaveRequest request = TodoMstSaveRequest.builder()
                .topicKey(topicKey)
                .title(title)
                .memo(memo)
                .build();

        for (int i = 0; i < requestTry; i++) {
            할일을_등록함(request);
        }

        Optional<Long> currentAuditor = auditorAware.getCurrentAuditor();

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<TodoMstResponse> result = 응답값을_TodoMst리스트로_매핑함(mvcResult);

        //then
        assertSoftly(softAssertions -> {
           assertThat(result.size()).isEqualTo(requestTry);
        });
    }

    @Test
    @DisplayName("잘못된 할 일을 단건 조회합니다.")
    void getException() throws Exception {
        //given
        String svcNo = "잘못된_svcNo";

        //when & then
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/" + svcNo))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName("할 일을 수정합니다.")
    void patch() throws Exception {
        //given
        String topicKey = "topicKey";
        String title = "title";
        String memo = "memo";

        TodoMstSaveRequest request = TodoMstSaveRequest.builder()
                .topicKey(topicKey)
                .title(title)
                .memo(memo)
                .build();

        TodoMstResponse todoMst = 할일을_등록함(request);

        String updateTitle = "updateTitle";
        TodoMstUpdateRequest updateRequest = TodoMstUpdateRequest.builder()
                .svcNo(todoMst.getSvcNo())
                .title(updateTitle)
                .build();

        String requestBody = objectMapper.writeValueAsString(updateRequest);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch(baseUrl + "/{svcNo}", todoMst.getSvcNo())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        TodoMst result = 응답값을_객체에_매핑함(mvcResult, TodoMst.class);

        //then
        assertSoftly(softAssertions -> {
            assertThat(result.getSvcNo()).isEqualTo(todoMst.getSvcNo());
            assertThat(result.getTopicKey()).isEqualTo(topicKey);
            assertThat(result.getTitle()).isEqualTo(updateTitle);
            assertThat(result.getMemo()).isEqualTo(memo);
        });
    }

    @Test
    @DisplayName("잘못된 할 일을 수정합니다.")
    void patchException() throws Exception {
        //given
        String svcNo = "잘못된_patch_svcNo";

        TodoMstUpdateRequest updateRequest = TodoMstUpdateRequest.builder()
                .svcNo(svcNo)
                .build();

        String requestBody = objectMapper.writeValueAsString(updateRequest);

        //when & then
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch(baseUrl + "/{svcNo}", svcNo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName("할 일을 삭제합니다.")
    void delete() throws Exception {
        //given
        String topicKey = "topicKey";
        String title = "title";
        String memo = "memo";

        TodoMstSaveRequest request = TodoMstSaveRequest.builder()
                .topicKey(topicKey)
                .title(title)
                .memo(memo)
                .build();

        TodoMstResponse todoMst = 할일을_등록함(request);
        String svcNo = todoMst.getSvcNo();

        //when & then
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/{svcNo}", svcNo))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("잘못된 할 일을 삭제합니다.")
    void deleteException() throws Exception {
        //given
        String svcNo = "잘못된_delete_svcNo";

        //when & then
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/{svcNo}", svcNo))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

    private TodoMstResponse 할일을_등록함(TodoMstSaveRequest todoMstSaveRequest) throws Exception {
        String requestBody = objectMapper.writeValueAsString(todoMstSaveRequest);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        MvcResult mvcSelectResult = mockMvc.perform(MockMvcRequestBuilders.get(mvcResult.getResponse().getRedirectedUrl()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        return 응답값을_객체에_매핑함(mvcSelectResult, TodoMstResponse.class);
    }

    private <T> T 응답값을_객체에_매핑함(MvcResult mvcResult, Class<T> type) throws Exception {
        Map<String, Object> responseMap = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        return objectMapper.readValue(objectMapper.writeValueAsString(responseMap.get("data")), type);
    }

    private List<TodoMstResponse> 응답값을_TodoMst리스트로_매핑함(MvcResult mvcResult) throws Exception {
        Map<String, Object> responseMap = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        return objectMapper.readValue(objectMapper.writeValueAsString(responseMap.get("data")), new TypeReference<List<TodoMstResponse>>() {
        });
    }
}