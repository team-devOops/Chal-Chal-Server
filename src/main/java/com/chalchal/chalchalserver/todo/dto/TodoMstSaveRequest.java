package com.chalchal.chalchalserver.todo.dto;

import lombok.Getter;

@Getter
public class TodoMstSaveRequest {
    private String topicKey;
    private String title;
    private String memo;
}
