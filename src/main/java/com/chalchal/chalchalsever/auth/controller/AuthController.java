package com.chalchal.chalchalsever.auth.controller;

import antlr.Token;
import com.chalchal.chalchalsever.auth.service.UserService;
import com.chalchal.chalchalsever.auth.service.UserTokenInfoService;
import com.chalchal.chalchalsever.config.jwt.JwtConfig;
import com.chalchal.chalchalsever.domain.User;
import com.chalchal.chalchalsever.dto.TokenResponse;
import com.chalchal.chalchalsever.dto.UserRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final UserTokenInfoService userTokenInfoService;
    private final JwtConfig jwtConfig;

    @PostMapping(value = "/join")
    @ApiOperation(value = "회원가입")
    public User signUp(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
        User user = userService.findByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword());

        TokenResponse tokenResponse = TokenResponse.builder()
                .id(user.getId())
                .ACCESS_TOKEN(jwtConfig.createToken(user.getEmail(), Arrays.asList(user.getUserRole().getValue())))
                .REFRESH_TOKEN(jwtConfig.createRefreshToken(user.getEmail(), Arrays.asList(user.getUserRole().getValue())))
                .build();

        userTokenInfoService.createUserTokenInfo(tokenResponse);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/info/{email}")
    @ApiOperation(value = "개인정보")
    public User getInfo(HttpServletRequest httpServletRequest, @PathVariable String email) {
        return userService.findUser(email);
    }
}
