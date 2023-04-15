package com.chalchal.chalchalserver.todo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoMstSaveRequest {
    private String topicKey;
    private String title;
    private String memo;

    @Builder
    public TodoMstSaveRequest(String topicKey, String title, String memo) {
        this.topicKey = topicKey;
        this.title = title;
        this.memo = memo;
    }
}
