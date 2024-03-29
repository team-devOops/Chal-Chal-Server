package com.chalchal.chalchalserver.auth.controller;

import com.chalchal.chalchalserver.auth.dto.AuthNumCompareRequest;
import com.chalchal.chalchalserver.auth.dto.UserAuthRequest;
import com.chalchal.chalchalserver.auth.domain.MailAuthNum;
import com.chalchal.chalchalserver.auth.domain.User;
import com.chalchal.chalchalserver.auth.domain.UserMailAuth;
import com.chalchal.chalchalserver.auth.service.UserAuthService;
import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.global.dto.ResultResponse;
import com.chalchal.chalchalserver.global.generate.SvcNo;
import com.chalchal.chalchalserver.mail.domain.MailDiv;
import com.chalchal.chalchalserver.mail.dto.MailRequest;
import com.chalchal.chalchalserver.mail.service.MailService;
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
@Api(tags = {"회원가입 : 이메일 발송 관련"})
@RequiredArgsConstructor
public class AuthMailController {
    private final UserAuthService userAuthService;
    private final MailService mailService;

    @PostMapping(value = "/{mailDiv}")
    @ApiOperation(value = "인증번호 이메일 발송", notes = "인증코드를 담은 이메일 전송")
    public ResponseEntity<ResultResponse<Object>> sendEmail(@PathVariable MailDiv mailDiv,
                                                            @AuthenticationPrincipal User user) {
        String authNum = MailAuthNum.creteMailAuthNum().value();

        UserMailAuth userMailAuth = userAuthService.createUserAuth(UserAuthRequest.builder()
                    .svcNo(SvcNo.getSvcNo())
                    .id(user.getId())
                    .email(user.getEmail())
                    .code(authNum)
                    .authYn(Flag.N)
                .build());

        //TODO: 이메일 형식을 템플릿 형식으로 추가 개발 필요
        mailService.mailSend(MailRequest.builder()
                    .svcNo(userMailAuth.getSvcNo())
                    .mailDiv(mailDiv)
                    .to(userMailAuth.getSendEmail())
                    .subject("TEST")
                    .content(authNum)
                .build());

        return ResultResponse.ok(ResultResponse.builder()
                    .data(userMailAuth)
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
