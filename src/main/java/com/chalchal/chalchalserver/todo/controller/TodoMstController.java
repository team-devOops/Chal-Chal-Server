package com.chalchal.chalchalserver.todo.controller;

import com.chalchal.chalchalserver.auth.domain.User;
import com.chalchal.chalchalserver.todo.dto.TodoMstResponse;
import com.chalchal.chalchalserver.todo.dto.TodoMstSaveRequest;
import com.chalchal.chalchalserver.todo.dto.TodoMstUpdateRequest;
import com.chalchal.chalchalserver.global.dto.ResultResponse;
import com.chalchal.chalchalserver.todo.entity.TodoMst;
import com.chalchal.chalchalserver.todo.service.TodoMstService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/todo")
@Api(tags = {"TODO : 할 일 관리"})
@RequiredArgsConstructor
public class TodoMstController {

    private final TodoMstService todoMstService;

    /**
     * 할 일 조회
     */
    @GetMapping
    @ApiOperation(value = "TODO 조회", notes = "할 일 내용 조회")
    public ResponseEntity<ResultResponse<TodoMstResponse>> selectAll(@AuthenticationPrincipal User user) {
        List<TodoMst> todoMstList = todoMstService.findTodoMstByRegId(user.getId());

        return ResultResponse.ok(ResultResponse.builder()
                    .data(todoMstList.stream().map(todoMst -> TodoMstResponse.from(todoMst)).collect(Collectors.toList()))
                .build());
    }

    /**
     * 할 일 단건 조회
     */
    @GetMapping("/{svcNo}")
    @ApiOperation(value = "TODO 조회", notes = "할 일 내용 조회")
    public ResponseEntity<ResultResponse<TodoMstResponse>> select(@AuthenticationPrincipal User user, @PathVariable String svcNo) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(todoMstService.findTodoMstBySvcNo(svcNo))
                .build());
    }

    /**
     * 할 일 등록하는 메소드
     */
    @PostMapping
    @ApiOperation(value = "TODO 작성", notes = "할 일 내용 등록")
    public ResponseEntity<Void> save(@AuthenticationPrincipal User user,
                                     @RequestBody TodoMstSaveRequest todoMstSaveRequest) {
        TodoMst todoMst = todoMstService.createTodoMst(todoMstSaveRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{svcNo}")
                .buildAndExpand(todoMst.getSvcNo())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * 할 일 수정하는 메소드
     */
    @PatchMapping(value = "/{svcNo}")
    @ApiOperation(value = "TODO 수정", notes = "할 일 내용 수정")
    public ResponseEntity<ResultResponse<TodoMstResponse>> update(@RequestBody TodoMstUpdateRequest todoMstUpdateRequest) {
        return ResultResponse.ok(ResultResponse.builder()
                    .data(todoMstService.updateTodoMst(todoMstUpdateRequest))
                    .message("수정 되었습니다.")
                .build());
    }

    /**
     * 할 일 삭제하는 메소드
     */
    @DeleteMapping(value = "/{svcNo}")
    @ApiOperation(value = "TODO 삭제", notes = "할 일 내용 삭제")
    public ResponseEntity<ResultResponse<Void>> delete(@PathVariable String svcNo) {
        ResultResponse.ok(ResultResponse.builder()
                .data(todoMstService.deleteTodoMst(svcNo))
                .message("삭제 되었습니다.")
                .build());

        return ResultResponse.ok();
    }
}
