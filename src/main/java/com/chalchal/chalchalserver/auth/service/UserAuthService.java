package com.chalchal.chalchalserver.auth.service;

import com.chalchal.chalchalserver.auth.repository.UserAuthRepository;
import com.chalchal.chalchalserver.auth.domain.UserJoinAuth;
import com.chalchal.chalchalserver.auth.dto.UserAuthRequest;
import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.global.exception.BaseException;
import com.chalchal.chalchalserver.global.exception.ErrorCode;
import com.chalchal.chalchalserver.global.generate.SvcNo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

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
    public UserJoinAuth createUserAuth(UserAuthRequest userAuthRequest) {
        return userAuthRepository.save(UserJoinAuth.builder()
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
    public boolean isAuth(Long id) {
        UserJoinAuth userJoinAuth = userAuthRepository.findTop1ByIdAndAuthYnAndValidDateAfterOrderByRegDateDesc(id, Flag.Y, LocalDateTime.now());
        return ObjectUtils.isEmpty(userJoinAuth) ? false : true;
    }

    /**
     * 제일 최근 인증내역 조회
     */
    public UserJoinAuth getUserJoinAuth(Long id) {
        return userAuthRepository.findTop1ByIdAndAuthYnAndValidDateAfterOrderByRegDateDesc(id, Flag.Y, LocalDateTime.now());
    }

    /**
     * 입력된 인증코드 비교 및 상태 저장
     */
    @Transactional
    public UserJoinAuth compareAuthNum(Long id, String authNum) {
        if(isAuth(id)) {
            throw new BaseException(ErrorCode.AUTH_ALREADY_DONE);
        }

        UserJoinAuth userJoinAuth = this.getUserJoinAuth(id);

        if(authNum.equals(userJoinAuth.getAuthCode())) {
            this.successAuth(userJoinAuth);
        }
        else {
            throw new BaseException(ErrorCode.AUTH_NUM_IS_NOT_COMPARE);
        }

        return userJoinAuth;
    }

    /**
     * 인증 성공 시, 인증 여부 및 시간 업데이트
     */
    @Transactional
    public UserJoinAuth successAuth(UserJoinAuth userJoinAuth) {
        userJoinAuth.successAuth();
        return userJoinAuth;
    }
}
