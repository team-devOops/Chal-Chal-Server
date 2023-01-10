package com.chalchal.chalchalsever.domain.todo.dto;

import lombok.Getter;

@Getter
public class TodoGroupSaveRequest {
    private String emoji;
    private String bgColor;
    private String useYn;
    //private Long orderSeq;
}
