package com.chalchal.chalchalserver.todo.controller;

import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.todo.dto.*;
import com.chalchal.chalchalserver.todo.entity.TodoMst;
import com.chalchal.chalchalserver.todo.entity.TodoTopic;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TodoTopicControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext ctx;

    String baseUrl;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        baseUrl = "/todo/topics";

        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("토픽을 등록합니다.")
    void post() throws Exception {
        //given
        String emoji = "emoji";
        String bgColor = "bgColor";
        Flag useYn = Flag.Y;

        TodoTopicSaveRequest request = TodoTopicSaveRequest.builder()
                .emoji(emoji)
                .bgColor(bgColor)
                .useYn(useYn)
                .build();

        //when
        TodoTopicResponse result = 할일_주제를_등록함(request);

        //then
        assertSoftly(softAssertions -> {
            assertThat(result.getEmoji()).isEqualTo(emoji);
            assertThat(result.getBgColor()).isEqualTo(bgColor);
            assertThat(result.getUseYn()).isEqualTo(useYn);
        });
    }

    @Test
    @DisplayName("토픽을 조회합니다.")
    void get() throws Exception {
        //given
        String emoji = "emoji";
        String bgColor = "bgColor";
        Flag useYn = Flag.Y;

        TodoTopicSaveRequest request = TodoTopicSaveRequest.builder()
                .emoji(emoji)
                .bgColor(bgColor)
                .useYn(useYn)
                .build();

        TodoTopicResponse todoTopic = 할일_주제를_등록함(request);

        String svcNo = todoTopic.getSvcNo();

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/{svcNo}", svcNo))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        TodoTopic result = 응답값을_객체에_매핑함(mvcResult, TodoTopic.class);

        //then
        assertSoftly(softAssertions -> {
            assertThat(result.getSvcNo()).isEqualTo(svcNo);
            assertThat(result.getEmoji()).isEqualTo(emoji);
            assertThat(result.getBgColor()).isEqualTo(bgColor);
            assertThat(result.getUseYn()).isEqualTo(useYn);
        });
    }

    @Test
    @DisplayName("잘못된 토픽을 조회합니다.")
    void getException() throws Exception {
        //given
        String svcNo = "잘못된_get_svcNo";

        //when & then
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/{svcNo}", svcNo))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName("토픽을 수정합니다.")
    void patch() throws Exception {
        //given
        String emoji = "emoji";
        String bgColor = "bgColor";
        Flag useYn = Flag.Y;

        TodoTopicSaveRequest request = TodoTopicSaveRequest.builder()
                .emoji(emoji)
                .bgColor(bgColor)
                .useYn(useYn)
                .build();

        TodoTopicResponse todoTopic = 할일_주제를_등록함(request);

        String svcNo = todoTopic.getSvcNo();
        String updateEmoji = "updateEmoji";
        TodoTopicUpdateRequest updateRequest = TodoTopicUpdateRequest.builder()
                .svcNo(svcNo)
                .emoji(updateEmoji)
                .build();

        String requestBody = objectMapper.writeValueAsString(updateRequest);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch(baseUrl + "/{svcNo}", svcNo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        TodoTopic result = 응답값을_객체에_매핑함(mvcResult, TodoTopic.class);

        //then
        assertSoftly(softAssertions -> {
            assertThat(result.getSvcNo()).isEqualTo(svcNo);
            assertThat(result.getEmoji()).isEqualTo(updateEmoji);
            assertThat(result.getBgColor()).isEqualTo(bgColor);
            assertThat(result.getUseYn()).isEqualTo(useYn);
        });
    }

    @Test
    @DisplayName("잘못된 토픽을 수정합니다.")
    void patchException() throws Exception {
        //given
        String svcNo = "잘못된_patch_svcNo";
        String updateEmoji = "updateEmoji";
        TodoTopicUpdateRequest updateRequest = TodoTopicUpdateRequest.builder()
                .svcNo(svcNo)
                .emoji(updateEmoji)
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
    @DisplayName("토픽을 삭제합니다.")
    void delete() throws Exception {
        //given
        String emoji = "emoji";
        String bgColor = "bgColor";
        Flag useYn = Flag.Y;

        TodoTopicSaveRequest request = TodoTopicSaveRequest.builder()
                .emoji(emoji)
                .bgColor(bgColor)
                .useYn(useYn)
                .build();

        TodoTopicResponse result = 할일_주제를_등록함(request);

        String svcNo = result.getSvcNo();

        //when & then
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/{svcNo}", svcNo))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @DisplayName("잘못된 토픽을 삭제합니다.")
    void deleteException() throws Exception {
        String svcNo = "잘못된_delete_svcNo";

        //when & then
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/{svcNo}", svcNo))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

    private TodoTopicResponse 할일_주제를_등록함(TodoTopicSaveRequest request) throws Exception {
        String requestBody = objectMapper.writeValueAsString(request);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        MvcResult mvcSelectResult = mockMvc.perform(MockMvcRequestBuilders.get(mvcResult.getResponse().getRedirectedUrl()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        return 응답값을_객체에_매핑함(mvcSelectResult, TodoTopicResponse.class);
    }

    private <T> T 응답값을_객체에_매핑함(MvcResult mvcResult, Class<T> type) throws Exception {
        Map<String, Object> responseMap = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        return objectMapper.readValue(objectMapper.writeValueAsString(responseMap.get("data")), type);
    }
}