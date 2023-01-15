package com.chalchal.chalchalsever.domain.auth.controller;

import com.chalchal.chalchalsever.domain.auth.dto.*;
import com.chalchal.chalchalsever.domain.auth.service.UserService;
import com.chalchal.chalchalsever.domain.auth.service.UserTokenInfoService;
import com.chalchal.chalchalsever.global.config.jwt.JwtUtils;
import com.chalchal.chalchalsever.domain.auth.entity.User;
import com.chalchal.chalchalsever.domain.auth.entity.UserTokenInfo;
import com.chalchal.chalchalsever.global.dto.ErrorResponse;
import com.chalchal.chalchalsever.global.dto.ResultResponse;
import com.chalchal.chalchalsever.global.exception.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@Api(tags = {"회원가입"})
@RequiredArgsConstructor
public class AuthController {

    private final static  String REFRESH_TOKEN_INDEX = "REFRESHTOKENINDEX";
    private final UserService userService;
    private final UserTokenInfoService userTokenInfoService;
    private final JwtUtils jwtUtils;

    @PostMapping(value = "/join")
    @ApiOperation(value = "회원가입")
    public ResponseEntity signUp(@RequestBody UserRequest userRequest) {
        if(!userService.validateRegister(userRequest.getEmail())) {
            return ErrorResponse.toErrorResponse(ErrorCode.DUPLICATE_RESOURCE);
        }

        return ResultResponse.ok(ResultResponse.builder()
                    .data(UserResponse.from(userService.createUser(userRequest)))
                .build());
    }

    @PostMapping(value = "/refresh")
    @ApiOperation(value = "access token 재발급")
    public ResponseEntity accessTokenRefresh(@AuthenticationPrincipal User user, HttpServletRequest httpServletRequest) {
        long refreshTokenIndex = jwtUtils.getRefreshTokenByCookieIndex(httpServletRequest, REFRESH_TOKEN_INDEX);

        UserTokenInfo userTokenInfo = userTokenInfoService.getTokenInfo(refreshTokenIndex);

        if(!jwtUtils.isValidRefreshToken(userTokenInfo)) {
            return ErrorResponse.toErrorResponse(ErrorCode.NOT_AUTHORIZED);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtUtils.createToken(user))
                .build();
    }

    @PostMapping("/sign-in")
    @ApiOperation(value = "로그인")
    public ResponseEntity<ResultResponse> login(@RequestBody LoginUserRequest userRequest) {
        User user = userService.findByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword());

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtUtils.createToken(user))
                .header(HttpHeaders.SET_COOKIE, userService.setRefreshTokenIndexCookie(jwtUtils.createRefreshToken(user)).toString())
                .body(ResultResponse.builder()
                    .status(HttpStatus.OK.value())
                    .data(UserResponse.from(user)).build()
                );
    }

    @PostMapping(value = "/sign-out")
    @ApiOperation(value = "로그아웃")
    public ResponseEntity<Void> logout(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok()
                .headers(userService.setLogout(httpServletRequest))
                .build();
    }

    @PostMapping(value = "/resign")
    @ApiOperation(value = "회원 탈퇴")
    public ResponseEntity<Void> resign(HttpServletRequest httpServletRequest) {
        Authentication authentication = jwtUtils.getAuthentication(jwtUtils.resolveToken(httpServletRequest));
        User user = (User) authentication.getPrincipal();

        userService.resignUser(user.getId());

        return ResponseEntity.ok()
                .headers(userService.setLogout(httpServletRequest))
                .build();
    }

    @PostMapping(value = "/info/{email}")
    @ApiOperation(value = "개인정보")
    public ResponseEntity getInfo(@PathVariable String email) {
        return ResultResponse.ok(ResultResponse.builder()
                    .data(UserResponse.from(userService.findUser(email)))
                .build());
    }
}
