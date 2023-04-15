package com.chalchal.chalchalserver.todo.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoTopicSaveRequest {
    private String emoji;
    private String bgColor;
    private Flag useYn;

    @Builder
    public TodoTopicSaveRequest(String emoji, String bgColor, Flag useYn) {
        this.emoji = emoji;
        this.bgColor = bgColor;
        this.useYn = useYn;
    }
}
