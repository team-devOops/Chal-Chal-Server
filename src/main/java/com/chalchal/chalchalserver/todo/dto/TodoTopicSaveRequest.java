package com.chalchal.chalchalserver.todo.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import lombok.Getter;

@Getter
public class TodoTopicSaveRequest {
    private String emoji;
    private String bgColor;
    private Flag useYn;
    //private Long orderSeq;
}
