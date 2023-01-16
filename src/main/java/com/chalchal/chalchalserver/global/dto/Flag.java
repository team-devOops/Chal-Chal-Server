package com.chalchal.chalchalserver.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Flag {
    Y("Y"),
    N("N");

    private String value;
}
