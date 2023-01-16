package com.chalchal.chalchalserver.domain.todo.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import lombok.Getter;

@Getter
public class TodoTopicUpdateRequest {
    private String svcNo;
    private String emoji;
    private String bgColor;
    private Flag useYn;
}
