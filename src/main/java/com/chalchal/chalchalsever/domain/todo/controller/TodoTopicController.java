package com.chalchal.chalchalsever.domain.todo.controller;

import com.chalchal.chalchalsever.domain.todo.dto.TodoTopicSaveRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoTopicUpdateRequest;
import com.chalchal.chalchalsever.domain.todo.service.TodoTopicService;
import com.chalchal.chalchalsever.global.dto.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ResultResponse> save(@RequestBody TodoTopicSaveRequest todoTopicSaveRequest) {
        return ResponseEntity.ok()
                .body(ResultResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("등록 되었습니다.")
                        .data(todoTopicService.createTodoTopic(todoTopicSaveRequest))
                        .build());
    }

    @PatchMapping(value = "/{svcNo}")
    @ApiOperation(value = "TODO 토픽 수정")
    public ResponseEntity<ResultResponse> update(@RequestBody TodoTopicUpdateRequest todoTopicUpdateRequest) {
        return ResponseEntity.ok()
                .body(ResultResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("수정 되었습니다.")
                        .data(todoTopicService.updateTodoTopic(todoTopicUpdateRequest))
                        .build());
    }

    @DeleteMapping(value = "/{svcNo}")
    @ApiOperation(value = "TODO 토픽 삭제")
    public ResponseEntity<ResultResponse> delete(@PathVariable String svcNo) {
        todoTopicService.deleteTodoTopic(svcNo);

        return ResponseEntity.ok()
                .body(ResultResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("삭제 완료 되었습니다.")
                        .data(null)
                        .build());
    }
}
