package com.chalchal.chalchalsever.domain.todo.dto;

import lombok.Getter;

@Getter
public class TodoListSaveRequest {
    private String topicKey;
    private String title;
    private String memo;
}
