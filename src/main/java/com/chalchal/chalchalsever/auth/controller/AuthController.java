package com.chalchal.chalchalsever.auth.controller;

import com.chalchal.chalchalsever.auth.service.UserAuthService;
import com.chalchal.chalchalsever.auth.service.UserService;
import com.chalchal.chalchalsever.auth.service.UserTokenInfoService;
import com.chalchal.chalchalsever.domain.UserJoinAuth;
import com.chalchal.chalchalsever.dto.*;
import com.chalchal.chalchalsever.global.config.jwt.JwtUtils;
import com.chalchal.chalchalsever.domain.User;
import com.chalchal.chalchalsever.domain.UserTokenInfo;
import com.chalchal.chalchalsever.global.mail.MailService;
import com.chalchal.chalchalsever.global.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
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
    private final UserAuthService userAuthService;
    private final UserTokenInfoService userTokenInfoService;
    private final JwtUtils jwtUtils;

    private final MailService mailService;

    @PostMapping(value = "/join")
    @ApiOperation(value = "회원가입")
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRequest userRequest) {
        if(userService.validateRegister(userRequest.getEmail())) {
            return new ResponseEntity<>(UserResponse.from(userService.createUser(userRequest)), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping(value = "/refresh")
    @ApiOperation(value = "access token 재발급")
    public ResponseEntity<ResultResponse> accessTokenRefresh(HttpServletRequest httpServletRequest) {
        long refreshTokenIndex = jwtUtils.getRefreshTokenByCookieIndex(httpServletRequest, REFRESH_TOKEN_INDEX);

        UserTokenInfo userTokenInfo = userTokenInfoService.getTokenInfo(refreshTokenIndex);

        if(jwtUtils.isValidRefreshToken(userTokenInfo)) {
            User user = userService.findUserById(userTokenInfo.getId());

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtUtils.createToken(user))
                    .build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .build();
    }

    @PostMapping("/sign-in")
    @ApiOperation(value = "로그인")
    public ResponseEntity<ResultResponse> login(@RequestBody UserRequest userRequest) {
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
    public ResponseEntity<UserResponse> getInfo(@PathVariable String email) {
        return ResponseEntity.ok(UserResponse.from(userService.findUser(email)));
    }

    @PostMapping(value = "/auth")
    @ApiOperation(value = "이메일 발송")
    public ResponseEntity<?> sendEmail(HttpServletRequest httpServletRequest) {
        Authentication authentication = jwtUtils.getAuthentication(jwtUtils.resolveToken(httpServletRequest));
        User user = (User) authentication.getPrincipal();

        String authCode = "123456";

        UserJoinAuth userJoinAuth = userAuthService.createUserAuth(UserAuthRequest.builder()
                    .reqSvcNo("@@@@")
                    .id(user.getId())
                    .email(user.getEmail())
                    .code(authCode)
                    .limitDate(DateUtils.getCurrentDay("YYYYMMDD"))
                    .limitTime(DateUtils.getCurrentTime("HHmm"))
                    .authYn("N")
                .build());

        MailRequest mailRequest = MailRequest.builder()
                    .to(user.getEmail())
                    .subject("TEST")
                    .text(authCode)
                .build();

        mailService.mailSend(mailRequest);

        return null;
    }
}
