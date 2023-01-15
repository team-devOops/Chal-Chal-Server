package com.chalchal.chalchalsever.domain.todo.controller;

import com.chalchal.chalchalsever.domain.todo.dto.TodoTopicSaveRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoTopicUpdateRequest;
import com.chalchal.chalchalsever.domain.todo.service.TodoTopicService;
import com.chalchal.chalchalsever.global.dto.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/todo/topic")
@Api(tags = {"TODO TOPIC"})
@RequiredArgsConstructor
public class TodoTopicController {

    private final TodoTopicService todoTopicService;

    @PostMapping(value = "/")
    @ApiOperation(value = "TODO 토픽 생성")
    public ResponseEntity save(@RequestBody TodoTopicSaveRequest todoTopicSaveRequest) {
        return ResultResponse.ok(ResultResponse.builder()
                    .data(todoTopicService.createTodoTopic(todoTopicSaveRequest))
                    .message("등록 되었습니다.")
                .build());
    }

    @PatchMapping(value = "/{svcNo}")
    @ApiOperation(value = "TODO 토픽 수정")
    public ResponseEntity update(@RequestBody TodoTopicUpdateRequest todoTopicUpdateRequest) {
        return ResultResponse.ok(ResultResponse.builder()
                    .data(todoTopicService.updateTodoTopic(todoTopicUpdateRequest))
                    .message("수정 되었습니다.")
                .build());
    }

    @DeleteMapping(value = "/{svcNo}")
    @ApiOperation(value = "TODO 토픽 삭제")
    public ResponseEntity delete(@PathVariable String svcNo) {
        return ResultResponse.ok(ResultResponse.builder()
                    .data(todoTopicService.deleteTodoTopic(svcNo))
                    .message("삭제 완료 되었습니다.")
                .build());
    }
}
