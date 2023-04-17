package com.chalchal.chalchalserver.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ResetRequest {
    @NotEmpty
    @Size(min = 8, max = 32)
    private String password;

    @NotEmpty
    @Size(min = 8, max = 32)
    private String checkPassword;

    @Builder
    public ResetRequest(String password, String checkPassword) {
        this.password = password;
        this.checkPassword = checkPassword;
    }
}
