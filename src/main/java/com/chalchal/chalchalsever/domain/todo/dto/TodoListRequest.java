package com.chalchal.chalchalsever.domain.todo.dto;

import lombok.Getter;

@Getter
public class TodoListRequest {
    private String groupKey;
    private String title;
    private String memo;
}
