package com.chalchal.chalchalsever.domain.todo.controller;

import com.chalchal.chalchalsever.domain.todo.dto.TodoGroupSaveRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoGroupUpdateRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoListSaveRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoListUpdateRequest;
import com.chalchal.chalchalsever.domain.todo.service.TodoService;
import com.chalchal.chalchalsever.global.config.jwt.JwtUtils;
import com.chalchal.chalchalsever.global.dto.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/todo")
@Api(tags = {"TODO"})
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final JwtUtils jwtUtils;

    /**
     * 할 일 조회
     */
    @GetMapping(value = "/")
    @ApiOperation(value = "TODO 조회")
    public ResponseEntity<ResultResponse> selectAll(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok()
                .body(ResultResponse.builder()
                        .status(HttpStatus.OK.value())
                        .data(todoService.findTodoListByRegId(jwtUtils.getLoginUserInfo(httpServletRequest).getId()))
                        .build());
    }

    /**
     * 할 일 등록하는 메소드
     */
    @PostMapping(value = "/")
    @ApiOperation(value = "TODO 작성")
    public ResponseEntity<ResultResponse> save(@RequestBody TodoListSaveRequest todoListSaveRequest) {
        return ResponseEntity.ok()
                .body(ResultResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("등록 되었습니다.")
                        .data(todoService.createTodoList(todoListSaveRequest))
                        .build());
    }

    /**
     * 할 일 수정하는 메소드
     */
    @PatchMapping(value = "/{svcNo}")
    @ApiOperation(value = "TODO 수정")
    public ResponseEntity<ResultResponse> update(@RequestBody TodoListUpdateRequest todoListUpdateRequest) {
        return ResponseEntity.ok()
                .body(ResultResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("수정 되었습니다.")
                        .data(todoService.updateTodoList(todoListUpdateRequest))
                        .build());
    }

    /**
     * 할 일 삭제하는 메소드
     */
    @DeleteMapping(value = "/{svcNo}")
    @ApiOperation(value = "TODO 삭제")
    public ResponseEntity<ResultResponse> delete(@PathVariable String svcNo) {
        todoService.deleteTodoList(svcNo);

        return ResponseEntity.ok()
                .body(ResultResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("삭제 완료 되었습니다.")
                        .data(null)
                        .build());
    }

    @PostMapping(value = "/group")
    @ApiOperation(value = "TODO 그룹 생성")
    public ResponseEntity<ResultResponse> saveGroup(@RequestBody TodoGroupSaveRequest todoGroupSaveRequest) {
        return ResponseEntity.ok()
                .body(ResultResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("등록 되었습니다.")
                        .data(todoService.createTodoGroup(todoGroupSaveRequest))
                        .build());
    }

    @PatchMapping(value = "/group/{svcNo}")
    @ApiOperation(value = "TODO 그룹 수정")
    public ResponseEntity<ResultResponse> saveGroup(@RequestBody TodoGroupUpdateRequest todoGroupUpdateRequest) {
        return ResponseEntity.ok()
                .body(ResultResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("수정 되었습니다.")
                        .data(todoService.updateTodoGroup(todoGroupUpdateRequest))
                        .build());
    }

    @DeleteMapping(value = "/group/{svcNo}")
    @ApiOperation(value = "TODO 그룹 수정")
    public ResponseEntity<ResultResponse> saveGroup(@PathVariable String svcNo) {
        todoService.deleteTodoGroup(svcNo);

        return ResponseEntity.ok()
                .body(ResultResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("삭제 완료 되었습니다.")
                        .data(null)
                        .build());
    }
}
