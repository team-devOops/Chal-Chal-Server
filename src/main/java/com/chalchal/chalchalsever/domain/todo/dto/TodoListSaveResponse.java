package com.chalchal.chalchalsever.domain.todo.dto;

import com.chalchal.chalchalsever.domain.todo.entity.TodoList;
import lombok.Getter;

@Getter
public class TodoListSaveResponse {
    private String svcNo;

    private String groupKey;
    private String title;
    private String memo;
    private String useYn;
    private String successYn;

    public TodoListSaveResponse(String svcNo, String groupKey, String title, String memo, String useYn, String successYn) {
        this.svcNo = svcNo;
        this.groupKey = groupKey;
        this.title = title;
        this.memo = memo;
        this.useYn = useYn;
        this.successYn = successYn;
    }

    public static TodoListSaveResponse from(TodoList todoList) {
        return new TodoListSaveResponse(todoList.getSvcNo(), todoList.getGroupKey(), todoList.getTitle(), todoList.getMemo(), todoList.getUseYn(), todoList.getSuccessYn());
    }
}
