package com.chalchal.chalchalserver.todo.controller;

import com.chalchal.chalchalserver.todo.dto.TodoTopicResponse;
import com.chalchal.chalchalserver.todo.dto.TodoTopicSaveRequest;
import com.chalchal.chalchalserver.todo.dto.TodoTopicUpdateRequest;
import com.chalchal.chalchalserver.global.dto.ResultResponse;
import com.chalchal.chalchalserver.todo.entity.TodoTopic;
import com.chalchal.chalchalserver.todo.service.TodoTopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/todo/topics")
@Api(tags = {"TODO : TOPIC 관리"})
@RequiredArgsConstructor
public class TodoTopicController {

    private final TodoTopicService todoTopicService;

    @PostMapping
    @ApiOperation(value = "TODO 토픽 생성")
    public ResponseEntity<ResultResponse<TodoTopicResponse>> save(@RequestBody TodoTopicSaveRequest todoTopicSaveRequest) {
        TodoTopic todoTopic = todoTopicService.createTodoTopic(todoTopicSaveRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{svcNo}")
                .buildAndExpand(todoTopic.getSvcNo())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{svcNo}")
    @ApiOperation(value = "TODO 조회")
    public ResponseEntity<ResultResponse<TodoTopicResponse>> select(@PathVariable String svcNo) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(todoTopicService.findTodoTopicBySvcNo(svcNo))
                .build());
    }

    @PatchMapping(value = "/{svcNo}")
    @ApiOperation(value = "TODO 토픽 수정")
    public ResponseEntity<ResultResponse<TodoTopicResponse>> update(@RequestBody TodoTopicUpdateRequest todoTopicUpdateRequest) {
        return ResultResponse.ok(ResultResponse.builder()
                    .data(todoTopicService.updateTodoTopic(todoTopicUpdateRequest))
                    .message("수정 되었습니다.")
                .build());
    }

    @DeleteMapping(value = "/{svcNo}")
    @ApiOperation(value = "TODO 토픽 삭제")
    public ResponseEntity<ResultResponse<Void>> delete(@PathVariable String svcNo) {
        return ResultResponse.ok();
    }
}
