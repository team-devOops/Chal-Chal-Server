package com.chalchal.chalchalserver.domain.auth.controller;

import com.chalchal.chalchalserver.domain.auth.dto.*;
import com.chalchal.chalchalserver.domain.auth.entity.MailAuthNum;
import com.chalchal.chalchalserver.domain.auth.entity.User;
import com.chalchal.chalchalserver.domain.auth.entity.UserJoinAuth;
import com.chalchal.chalchalserver.domain.auth.service.UserAuthService;
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
@Api(tags = {"회원가입 : 이메일 인증코드 인증"})
@RequiredArgsConstructor
public class AuthMailController {
    private final UserAuthService userAuthService;
    private final MailService mailService;

    @PostMapping(value = "/")
    @ApiOperation(value = "인증번호 이메일 발송", notes = "가입인증을 위한 인증코드를 담은 이메일 전송")
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

        //TODO: 이메일 형식을 템플릿 형식으로 추가 개발 필요
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
    @ApiOperation(value = "인증코드 비교", notes = "사용자가 입력한 코드와, 실제 발급된 코드가 유효한지 비교")
    public ResponseEntity<ResultResponse<Void>> compareAuthNum(@PathVariable String authNum,
                                                               @AuthenticationPrincipal AuthNumCompareRequest user) {
        userAuthService.compareAuthNum(user.getId(), authNum);

        return ResultResponse.ok();
    }
}
