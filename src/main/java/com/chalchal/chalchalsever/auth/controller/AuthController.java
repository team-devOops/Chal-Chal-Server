package com.chalchal.chalchalsever.auth.controller;

import com.chalchal.chalchalsever.auth.service.UserService;
import com.chalchal.chalchalsever.auth.service.UserTokenInfoService;
import com.chalchal.chalchalsever.config.jwt.JwtUtils;
import com.chalchal.chalchalsever.domain.User;
import com.chalchal.chalchalsever.domain.UserTokenInfo;
import com.chalchal.chalchalsever.dto.ResultResponse;
import com.chalchal.chalchalsever.dto.TokenResponse;
import com.chalchal.chalchalsever.dto.UserRequest;
import com.chalchal.chalchalsever.dto.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

    private final JavaMailSender mailSender;

    @PostMapping(value = "/join")
    @ApiOperation(value = "회원가입")
    public ResponseEntity<?> signUp(@RequestBody UserRequest userRequest) {
        if(userService.validateRegister(userRequest.getEmail())) {
            return new ResponseEntity<>(UserResponse.from(userService.createUser(userRequest)), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping(value = "/refresh")
    @ApiOperation(value = "access token 재발급")
    public ResponseEntity<?> accessTokenRefresh(HttpServletRequest httpServletRequest) {
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

        return new ResponseEntity<>(ResultResponse.builder().status(HttpStatus.OK.value()).data(UserResponse.from(user)), httpHeaders, HttpStatus.OK);
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
        return new ResponseEntity<>(UserResponse.from(userService.findUser(email)), HttpStatus.OK);
    }

    @PostMapping(value = "/auth")
    @ApiOperation(value = "이메일 발송")
    public ResponseEntity<?> sendEmail(HttpServletRequest httpServletRequest) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setFrom("zz");
            helper.setTo("xx");
            helper.setSubject("xx");
            // true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
            helper.setText("xx",true);
            mailSender.send(message);
        } catch ( MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
