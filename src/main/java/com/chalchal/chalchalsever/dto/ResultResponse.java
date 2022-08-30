package com.chalchal.chalchalsever.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ResultResponse {
    private int status;
    private String message;
}
