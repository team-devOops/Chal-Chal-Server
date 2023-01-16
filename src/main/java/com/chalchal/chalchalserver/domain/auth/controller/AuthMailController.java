package com.chalchal.chalchalserver.domain.auth.controller;

import com.chalchal.chalchalserver.domain.auth.dto.*;
import com.chalchal.chalchalserver.domain.auth.entity.MailAuthNum;
import com.chalchal.chalchalserver.domain.auth.entity.User;
import com.chalchal.chalchalserver.domain.auth.entity.UserJoinAuth;
import com.chalchal.chalchalserver.domain.auth.service.UserAuthService;
import com.chalchal.chalchalserver.global.config.jwt.JwtUtils;
import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.global.dto.ResultResponse;
import com.chalchal.chalchalserver.global.generate.SvcNo;
import com.chalchal.chalchalserver.global.mail.dto.MailRequest;
import com.chalchal.chalchalserver.global.mail.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth/mail")
@Api(tags = {"회원가입 - 로그인 인증"})
@RequiredArgsConstructor
public class AuthMailController {
    private final UserAuthService userAuthService;
    private final MailService mailService;
    private final JwtUtils jwtUtils;

    @PostMapping(value = "/")
    @ApiOperation(value = "이메일 발송")
    public ResponseEntity<ResultResponse<Object>> sendEmail(@RequestBody UserAuthMailRequest userAuthMailRequest,
                                                    @AuthenticationPrincipal User user) {
        String authNum = MailAuthNum.creteMailAuthNum().value();

        UserJoinAuth userJoinAuth = userAuthService.createUserAuth(UserAuthRequest.builder()
                    .svcNo(SvcNo.getSvcNo())
                    .id(user.getId())
                    .email(userAuthMailRequest.getEmail())
                    .code(authNum)
                    .authYn(Flag.N)
                .build());

        mailService.mailSend(MailRequest.builder()
                .svcNo(userJoinAuth.getSvcNo())
                .to(userAuthMailRequest.getEmail())
                .subject("TEST")
                .content(authNum)
                .build());

        return ResultResponse.ok(ResultResponse.builder()
                        .data(userJoinAuth)
                    .build());
    }

    @GetMapping(value = "/{authNum}")
    @ApiOperation(value = "인증코드 비교")
    public ResponseEntity<ResultResponse<Void>> compareAuthNum(@PathVariable String authNum,
                                                         @AuthenticationPrincipal User user) {
        userAuthService.compareAuthNum(user.getId(), authNum);

        return ResultResponse.ok();
    }
}
