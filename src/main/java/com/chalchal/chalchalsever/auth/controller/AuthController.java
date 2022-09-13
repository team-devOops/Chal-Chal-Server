package com.chalchal.chalchalsever.auth.controller;

import com.chalchal.chalchalsever.auth.service.UserService;
import com.chalchal.chalchalsever.auth.service.UserTokenInfoService;
import com.chalchal.chalchalsever.config.jwt.JwtUtils;
import com.chalchal.chalchalsever.domain.User;
import com.chalchal.chalchalsever.domain.UserTokenInfo;
import com.chalchal.chalchalsever.dto.ResultResponse;
import com.chalchal.chalchalsever.dto.TokenResponse;
import com.chalchal.chalchalsever.dto.UserRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@Api(tags = {"회원가입"})
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserTokenInfoService userTokenInfoService;
    private final JwtUtils jwtUtils;

    @PostMapping(value = "/join")
    @ApiOperation(value = "회원가입")
    public ResponseEntity<?> signUp(@RequestBody UserRequest userRequest) {
        if(userService.validateRegister(userRequest.getEmail())) {
            return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping(value = "/refresh")
    @ApiOperation(value = "access token 재발급")
    public ResponseEntity<?> accessTokenRefresh(HttpServletRequest httpServletRequest) {
        long refreshTokenIndex = jwtUtils.getRefreshTokenByCookieIndex(httpServletRequest, "REFRESHTOKENINDEX");

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
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
        User user = userService.findByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword());

        TokenResponse tokenResponse = TokenResponse.builder()
                .id(user.getId())
                .accessToken(jwtUtils.createToken(user))
                .refreshTokenIndex(jwtUtils.createRefreshToken(user))
                .build();

        ResponseCookie responseCookie = userService.setCookie(tokenResponse);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, tokenResponse.getAccessToken());
        httpHeaders.add(HttpHeaders.SET_COOKIE, responseCookie.toString());

        return new ResponseEntity<>(ResultResponse.builder().status(HttpStatus.OK.value()).data(user), httpHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "/sign-out")
    @ApiOperation(value = "로그아웃")
    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(userService.setLogout(httpServletRequest), HttpStatus.OK);
    }

    @PostMapping(value = "/resign")
    @ApiOperation(value = "회원 탈퇴")
    public ResponseEntity<?> resign(HttpServletRequest httpServletRequest) {
        Authentication authentication = jwtUtils.getAuthentication(jwtUtils.resolveToken(httpServletRequest));
        User user = (User) authentication.getPrincipal();

        return new ResponseEntity<>(userService.resignUser(user.getId()), userService.setLogout(httpServletRequest), HttpStatus.OK);
    }

    @PostMapping(value = "/info/{email}")
    @ApiOperation(value = "개인정보")
    public ResponseEntity<?> getInfo(@PathVariable String email) {
        return new ResponseEntity<>(userService.findUser(email), HttpStatus.OK);
    }
}
