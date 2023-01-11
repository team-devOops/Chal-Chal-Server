package com.chalchal.chalchalsever.domain.todo.dto;

import com.chalchal.chalchalsever.global.dto.Flag;
import lombok.Getter;

@Getter
public class TodoGroupUpdateRequest {
    private String svcNo;
    private String emoji;
    private String bgColor;
    private Flag useYn;
}
