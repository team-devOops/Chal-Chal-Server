package com.chalchal.chalchalserver.todo.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoTopicUpdateRequest {
    private String svcNo;
    private String emoji;
    private String bgColor;
    private Flag useYn;

    @Builder
    public TodoTopicUpdateRequest(String svcNo, String emoji, String bgColor, Flag useYn) {
        this.svcNo = svcNo;
        this.emoji = emoji;
        this.bgColor = bgColor;
        this.useYn = useYn;
    }
}
