package com.chalchal.chalchalserver.auth.service;

import com.chalchal.chalchalserver.auth.domain.UserMailAuth;
import com.chalchal.chalchalserver.auth.repository.UserRepository;
import com.chalchal.chalchalserver.auth.jwt.JwtUtils;
import com.chalchal.chalchalserver.auth.domain.User;
import com.chalchal.chalchalserver.auth.domain.UserTokenInfo;
import com.chalchal.chalchalserver.auth.dto.TokenResponse;
import com.chalchal.chalchalserver.auth.dto.UserRequest;
import com.chalchal.chalchalserver.global.dto.Flag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;

import static com.chalchal.chalchalserver.global.exception.BaseException.MENEBER_NOT_FOUND_EXCEPTION;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final static  String REFRESH_TOKEN_INDEX = "REFRESHTOKENINDEX";

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final UserTokenInfoService userTokenInfoService;

    /**
     * 회원 가입
     * 회원 가입때 저장 할 때 사용
     *
     * @return User 저장 된 후 내용 반환
     */
    @Retryable(value = SQLException.class)
    public User createUser(UserRequest userRequest) {
        User user = userRepository.save(User.builder()
                .email(userRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(userRequest.getPassword()))
                .userId(userRequest.getUserId())
                .nickname(userRequest.getNickName())
                .useYn(Flag.Y)
                .privateYn(Flag.N)
                .build());

        return user;
    }

    /**
     * EMAIL, USE_YN이 'Y'인 회원 정보 단건 조회
     *
     * @return User 조회 된 내용 반환
     */
    @Transactional(readOnly = true)
    public User findUser(String email) {
        return userRepository.findByEmailAndUseYn(email, Flag.Y)
                .orElseThrow(() -> MENEBER_NOT_FOUND_EXCEPTION);
    }

    /**
     * ID 기준 회원 정보 단건 조회
     *
     * @return User 조회 된 내용 반환
     */
    @Transactional(readOnly = true)
    public User findUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> MENEBER_NOT_FOUND_EXCEPTION);
    }

    /**
     * EMAIL, PASSWORD, USE_YN이 'Y'인 회원 조회
     *
     * @return User PASSWORD가 일치하는 회원 결과 반환
     */
    @Transactional(readOnly = true)
    public User findByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmailAndUseYn(email, Flag.Y)
                .orElseThrow(() -> MENEBER_NOT_FOUND_EXCEPTION);

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw MENEBER_NOT_FOUND_EXCEPTION;
        }

        return user;
    }

    /**
     * 회원 탈퇴
     * 탈퇴한 회원의 USE_YN을 'N'으로 수정
     *
     * @return User 수정된 회원 정보 반환
     */
    @Transactional
    @Retryable(value = SQLException.class)
    public User resignUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> MENEBER_NOT_FOUND_EXCEPTION);
        user.changeUseYn(Flag.N);

        return user;
    }

    /**
     * EMAIL 기준으로 데이터가 있는지 유효성 검사
     *
     * @return true : 데이터가 없는 경우 (미가입)
     *         false : 데이터가 있는 경우 (중복된 이메일)
     */
    @Transactional(readOnly = true)
    public boolean validateRegister(String email) {
        if(userRepository.countByEmail(email) > 0L) {
            return false;
        }

        return true;
    }

    /**
     * 로그아웃
     * 기존 token 및 cookie 정보 삭제
     */
    public HttpHeaders setLogout(HttpServletRequest httpServletRequest) {
        long refreshTokenIndex = jwtUtils.getRefreshTokenByCookieIndex(httpServletRequest, REFRESH_TOKEN_INDEX);
        UserTokenInfo userTokenInfo = userTokenInfoService.getTokenInfo(refreshTokenIndex);

        TokenResponse tokenResponse = TokenResponse.builder()
                .id(userTokenInfo.getId())
                .refreshTokenIndex(refreshTokenIndex)
                .refreshToken(null)
                .build();

        jwtUtils.insertRefreshTokenInfo(tokenResponse);

        ResponseCookie responseCookie = ResponseCookie.from(REFRESH_TOKEN_INDEX, null)
                .httpOnly(true)
                .maxAge(0)
                .secure(true)
                .path("/")
                .secure(true)
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, responseCookie.toString());

        return httpHeaders;
    }

    public ResponseCookie setCookie(TokenResponse tokenResponse) {
        return setRefreshTokenIndexCookie(tokenResponse.getRefreshTokenIndex());
    }

    public ResponseCookie setRefreshTokenIndexCookie(long refreshTokenIndex) {
        return ResponseCookie.from(REFRESH_TOKEN_INDEX, String.valueOf(refreshTokenIndex))
                .httpOnly(true)
                .secure(true)
                .path("/")
                .secure(true)
                .build();
    }



    /**
     * 비밀번호 업데이트
     */
    @Transactional
    @Retryable(value = SQLException.class)
    public User resetPassword(long id, String password) {
        User user = this.findUserById(id);
        user.encodePassword(password);
        return user;
    }
}
