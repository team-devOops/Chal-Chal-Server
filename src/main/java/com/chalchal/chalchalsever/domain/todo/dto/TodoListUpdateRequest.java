package com.chalchal.chalchalsever.domain.todo.dto;

import lombok.Getter;

/**
 * 할 일 수정시 필요한 객체
 */
@Getter
public class TodoListUpdateRequest {
    private String svcNo;
    private String groupKey;
    private String title;
    private String memo;
    private String useYn;
    private String successYn;
}
