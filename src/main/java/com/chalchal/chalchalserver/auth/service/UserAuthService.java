package com.chalchal.chalchalserver.auth.service;

import com.chalchal.chalchalserver.auth.domain.User;
import com.chalchal.chalchalserver.auth.repository.UserAuthRepository;
import com.chalchal.chalchalserver.auth.domain.UserMailAuth;
import com.chalchal.chalchalserver.auth.dto.UserAuthRequest;
import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.global.exception.BaseException;
import com.chalchal.chalchalserver.global.exception.ErrorCode;
import com.chalchal.chalchalserver.global.generate.SvcNo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static com.chalchal.chalchalserver.global.exception.BaseException.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserAuthRepository userAuthRepository;

    /**
     * 인증 내역 저장
     * 인증 번호 이메일 전송 내역 저장
     * @return UserJoinAuth 저장된 내용 반환
     */
    @Retryable(value = SQLException.class)
    public UserMailAuth createUserAuth(UserAuthRequest userAuthRequest) {
        return userAuthRepository.save(UserMailAuth.builder()
                    .svcNo(SvcNo.getSvcNo())
                    .id(userAuthRequest.getId())
                    .sendEmail(userAuthRequest.getEmail())
                    .authCode(userAuthRequest.getCode())
                    .validDate(userAuthRequest.getValidDate())
                    .authYn(Flag.N)
                .build());
    }

    /**
     * 인증 완료 된 이력이 있는지 확인
     * @return true 인증 내역 있음
     *         false 인증 내역 없음 (신규회원)
     */
    @Transactional(readOnly = true)
    public boolean isAuth(Long id) {
        return userAuthRepository.findTop1ByIdAndAuthYnAndValidDateAfterOrderByRegDateDesc(id, Flag.Y, LocalDateTime.now())
                .isEmpty();
    }

    /**
     * 마지막(최근) 인증내역 조회
     */
    @Transactional(readOnly = true)
    public UserMailAuth getLastUserJoinAuth(Long id) {
        return userAuthRepository
                .findTop1ByIdAndAuthYnAndValidDateAfterOrderByRegDateDesc(id, Flag.N, LocalDateTime.now())
                .orElseThrow(() -> AUTH_NOT_FOUND_EXCEPTION);
    }

    /**
     * 입력된 인증코드 비교 및 상태 저장
     */
    @Transactional
    public UserMailAuth compareAuthNum(Long id, String authNum) {
        UserMailAuth userMailAuth = this.getLastUserJoinAuth(id);

        if (isAuth(id)) {
            throw AUTH_ALREADY_DONE_EXCEPTION;
        }

        if (userMailAuth.isEqualAuthCode(authNum)) {
            throw new BaseException(ErrorCode.AUTH_NUM_IS_NOT_COMPARE);
        }

        return this.successAuth(userMailAuth);
    }

    /**
     * 인증 성공 시, 인증 여부 및 시간 업데이트
     */
    @Transactional
    @Retryable(value = SQLException.class)
    public UserMailAuth successAuth(UserMailAuth userMailAuth) {
        userMailAuth.successAuth();
        return userMailAuth;
    }
}
