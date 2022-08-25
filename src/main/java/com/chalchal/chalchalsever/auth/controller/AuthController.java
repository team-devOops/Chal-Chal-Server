package com.chalchal.chalchalsever.auth.controller;

import com.chalchal.chalchalsever.auth.service.UserService;
import com.chalchal.chalchalsever.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Api(tags = {"회원가입"})
@RequiredArgsConstructor
public class AuthController {

    private static UserService userService;

    @PostMapping(value = "/join")
    @ApiOperation(value = "회원가입")
    public User signUp(User user) {
        return userService.saveUser(user);
    }
}
