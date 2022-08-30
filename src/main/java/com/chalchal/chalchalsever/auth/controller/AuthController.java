package com.chalchal.chalchalsever.auth.controller;

import com.chalchal.chalchalsever.auth.service.UserService;
import com.chalchal.chalchalsever.config.jwt.JwtConfig;
import com.chalchal.chalchalsever.domain.User;
import com.chalchal.chalchalsever.dto.UserRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@Api(tags = {"회원가입"})
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtConfig jwtConfig;

    @PostMapping(value = "/join")
    @ApiOperation(value = "회원가입")
    public User signUp(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인")
    public String login(@RequestBody UserRequest userRequest) {
        User user = userService.findByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword());
        return jwtConfig.createToken(user.getEmail(), Arrays.asList(user.getUserRole().getValue()));
    }

    @PostMapping(value = "/info/{email}")
    @ApiOperation(value = "개인정보")
    public User getInfo(HttpServletRequest httpServletRequest, @PathVariable String email) {
        return userService.findUser(email);
    }
}
