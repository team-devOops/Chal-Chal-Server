package com.chalchal.chalchalsever.domain.todo.controller;

import com.chalchal.chalchalsever.domain.todo.dto.TodoListRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/todo")
@Api(tags = {"TODO"})
@RequiredArgsConstructor
public class TodoController {

    @PostMapping(value = "/create")
    @ApiOperation(value = "TODO 작성")
    public ResponseEntity<TodoListResponse> create(@RequestBody TodoListRequest todoListRequest) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
