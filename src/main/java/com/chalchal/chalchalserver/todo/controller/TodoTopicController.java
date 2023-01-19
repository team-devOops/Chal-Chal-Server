package com.chalchal.chalchalserver.todo.controller;

import com.chalchal.chalchalserver.todo.dto.TodoTopicSaveRequest;
import com.chalchal.chalchalserver.todo.dto.TodoTopicUpdateRequest;
import com.chalchal.chalchalserver.global.dto.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/todo/topic")
@Api(tags = {"TODO : TOPIC 관리"})
@RequiredArgsConstructor
public class TodoTopicController {

    private final TodoTopicService todoTopicService;

    @PostMapping(value = "/")
    @ApiOperation(value = "TODO 토픽 생성")
    public ResponseEntity<ResultResponse<Object>> save(@RequestBody TodoTopicSaveRequest todoTopicSaveRequest) {
        return ResultResponse.ok(ResultResponse.builder()
                    .data(todoTopicService.createTodoTopic(todoTopicSaveRequest))
                    .message("등록 되었습니다.")
                .build());
    }

    @PatchMapping(value = "/{svcNo}")
    @ApiOperation(value = "TODO 토픽 수정")
    public ResponseEntity<ResultResponse<Object>> update(@RequestBody TodoTopicUpdateRequest todoTopicUpdateRequest) {
        return ResultResponse.ok(ResultResponse.builder()
                    .data(todoTopicService.updateTodoTopic(todoTopicUpdateRequest))
                    .message("수정 되었습니다.")
                .build());
    }

    @DeleteMapping(value = "/{svcNo}")
    @ApiOperation(value = "TODO 토픽 삭제")
    public ResponseEntity<ResultResponse<Object>> delete(@PathVariable String svcNo) {
        return ResultResponse.ok(ResultResponse.builder()
                    .data(todoTopicService.deleteTodoTopic(svcNo))
                    .message("삭제 완료 되었습니다.")
                .build());
    }
}
