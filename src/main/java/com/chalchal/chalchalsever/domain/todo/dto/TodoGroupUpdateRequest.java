package com.chalchal.chalchalsever.domain.todo.dto;

import lombok.Getter;

@Getter
public class TodoGroupUpdateRequest {
    private String svcNo;
    private String emoji;
    private String bgColor;
    private String useYn;
}
