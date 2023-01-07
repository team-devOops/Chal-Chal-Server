package com.chalchal.chalchalsever.domain.todo.dto;

import com.chalchal.chalchalsever.domain.todo.entity.TodoList;
import lombok.Getter;

@Getter
public class TodoListResponse {
    private String svcNo;

    private String groupKey;
    private String title;
    private String memo;
    private char useYn;
    private char successYn;

    public TodoListResponse(String svcNo, String groupKey, String title, String memo, char useYn, char successYn) {
        this.svcNo = svcNo;
        this.groupKey = groupKey;
        this.title = title;
        this.memo = memo;
        this.useYn = useYn;
        this.successYn = successYn;
    }

    public static TodoListResponse from(TodoList todoList) {
        return new TodoListResponse(todoList.getSvcNo(), todoList.getGroupKey(), todoList.getTitle(), todoList.getMemo(), todoList.getUseYn(), todoList.getSuccessYn());
    }
}
