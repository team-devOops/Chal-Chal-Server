package com.chalchal.chalchalserver.auth.dto;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class ResetRequest {
    @NotEmpty
    private String password;
    @NotEmpty
    private String checkPassword;
}
