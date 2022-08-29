package com.chalchal.chalchalsever.auth.controller;

import com.chalchal.chalchalsever.auth.repository.UserRepository;
import com.chalchal.chalchalsever.auth.service.UserService;
import com.chalchal.chalchalsever.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
@Api(tags = {"회원가입"})
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(value = "/join")
    @ApiOperation(value = "회원가입")
    public User signUp(String email, String password) {
        User user = new User();
        return userService.createUser(user);
    }
}
