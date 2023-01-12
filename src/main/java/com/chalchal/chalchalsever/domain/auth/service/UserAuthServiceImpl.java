package com.chalchal.chalchalsever.domain.auth.service;

import com.chalchal.chalchalsever.domain.auth.repository.UserAuthRepository;
import com.chalchal.chalchalsever.domain.auth.entity.UserJoinAuth;
import com.chalchal.chalchalsever.domain.auth.dto.UserAuthRequest;
import com.chalchal.chalchalsever.global.dto.Flag;
import com.chalchal.chalchalsever.global.generate.SvcNo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserAuthRepository userAuthRepository;

    @Override
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
    @Override
    public boolean isAuth(Long id) {
        UserJoinAuth userJoinAuth = userAuthRepository.findTop1ByIdAndAuthYnOrderByRegDateDesc(id, Flag.Y);
        return ObjectUtils.isEmpty(userJoinAuth) ? false : true;
    }

    /**
     * 제일 최근 인증내역 조회
     */
    @Override
    public UserJoinAuth getUserJoinAuth(Long id) {
        //TODO: 유효시간 조건도 걸어주어야 함
        return userAuthRepository.findTop1ByIdAndAuthYnOrderByRegDateDesc(id, Flag.N);
    }

    /**
     * 입력된 인증코드 비교 및 상태 저장
     */
    @Override
    @Transactional
    public UserJoinAuth compareAuthNum(Long id, String authNum) {
        //이미 인증 한 유저
        if(isAuth(id)) {
            //return 에러 메세지
        }
        UserJoinAuth userJoinAuth = this.getUserJoinAuth(id);

        if(authNum.equals(userJoinAuth.getAuthCode())) {
            //인증 성공
            this.successAuth(userJoinAuth);
        }
        else {
            //인증 실패
        }

        return userJoinAuth;
    }

    /**
     * 인증 성공 시, 인증 여부 및 시간 업데이트
     */
    @Override
    @Transactional
    public UserJoinAuth successAuth(UserJoinAuth userJoinAuth) {
        userJoinAuth.successAuth();
        return userJoinAuth;
    }


}
