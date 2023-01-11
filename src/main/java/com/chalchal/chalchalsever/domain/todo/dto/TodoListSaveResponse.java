package com.chalchal.chalchalsever.domain.todo.dto;

import com.chalchal.chalchalsever.domain.todo.entity.TodoList;
import com.chalchal.chalchalsever.global.dto.Flag;
import lombok.Getter;

@Getter
public class TodoListSaveResponse {
    private String svcNo;
    private String topicKey;
    private String title;
    private String memo;
    private Flag useYn;
    private Flag successYn;

    public TodoListSaveResponse(String svcNo, String topicKey, String title, String memo, Flag useYn, Flag successYn) {
        this.svcNo = svcNo;
        this.topicKey = topicKey;
        this.title = title;
        this.memo = memo;
        this.useYn = useYn;
        this.successYn = successYn;
    }

    public static TodoListSaveResponse from(TodoList todoList) {
        return new TodoListSaveResponse(todoList.getSvcNo(), todoList.getTopicKey(), todoList.getTitle(), todoList.getMemo(), todoList.getUseYn(), todoList.getSuccessYn());
    }
}
