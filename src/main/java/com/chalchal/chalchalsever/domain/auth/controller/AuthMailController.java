package com.chalchal.chalchalsever.domain.auth.controller;

import com.chalchal.chalchalsever.domain.auth.dto.*;
import com.chalchal.chalchalsever.domain.auth.entity.MailAuthNum;
import com.chalchal.chalchalsever.domain.auth.entity.User;
import com.chalchal.chalchalsever.domain.auth.entity.UserJoinAuth;
import com.chalchal.chalchalsever.domain.auth.service.UserAuthService;
import com.chalchal.chalchalsever.global.config.jwt.JwtUtils;
import com.chalchal.chalchalsever.global.dto.Flag;
import com.chalchal.chalchalsever.global.dto.ResultResponse;
import com.chalchal.chalchalsever.global.generate.SvcNo;
import com.chalchal.chalchalsever.global.mail.dto.MailRequest;
import com.chalchal.chalchalsever.global.mail.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseEntity<ResultResponse> sendEmail(@RequestBody UserAuthMailRequest userAuthMailRequest,
                                                    HttpServletRequest httpServletRequest) {
        Authentication authentication = jwtUtils.getAuthentication(jwtUtils.resolveToken(httpServletRequest));
        User user = (User) authentication.getPrincipal();

        MailAuthNum mailAuthNum = new MailAuthNum();

        UserJoinAuth userJoinAuth = userAuthService.createUserAuth(UserAuthRequest.builder()
                    .svcNo(SvcNo.getSvcNo())
                    .id(user.getId())
                    .email(userAuthMailRequest.getEmail())
                    .code(mailAuthNum.getAuthNum())
                    .authYn(Flag.N)
                .build());

        MailRequest mailRequest = MailRequest.builder()
                    .svcNo(userJoinAuth.getSvcNo())
                    .to(userAuthMailRequest.getEmail())
                    .subject("TEST")
                    .content(mailAuthNum.getAuthNum())
                .build();

        //TODO:에러메세지 처리법이 필요해 보인다... ㅠ ㅠ
        return ResponseEntity.ok()
                .body(ResultResponse.builder()
                .status(mailService.mailSend(mailRequest).value())
                .build()
        );
    }

    @GetMapping(value = "/{authNum}")
    @ApiOperation(value = "개인정보")
    public ResponseEntity<ResultResponse> compareAuthNum(@PathVariable String authNum,
                                                         HttpServletRequest httpServletRequest) {
        //TODO: Q.아래 이 부분 중복을 제거하는 방법이 있을까여
        Authentication authentication = jwtUtils.getAuthentication(jwtUtils.resolveToken(httpServletRequest));
        User user = (User) authentication.getPrincipal();

        userAuthService.compareAuthNum(user.getId(), authNum);

        //TODO: 결과값도 처리 해야함 ...
        return ResponseEntity.ok()
                .body(ResultResponse.builder())
                .status(200)
                .build();
    }
}
