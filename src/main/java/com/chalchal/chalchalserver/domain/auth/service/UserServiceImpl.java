package com.chalchal.chalchalserver.domain.auth.service;

import com.chalchal.chalchalserver.domain.auth.repository.UserRepository;
import com.chalchal.chalchalserver.global.config.jwt.JwtUtils;
import com.chalchal.chalchalserver.domain.auth.entity.User;
import com.chalchal.chalchalserver.domain.auth.entity.UserTokenInfo;
import com.chalchal.chalchalserver.domain.auth.dto.TokenResponse;
import com.chalchal.chalchalserver.domain.auth.dto.UserRequest;
import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.global.exception.BaseException;
import com.chalchal.chalchalserver.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final static  String REFRESH_TOKEN_INDEX = "REFRESHTOKENINDEX";

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final UserTokenInfoService userTokenInfoService;

    private final static BaseException MENEBER_NOT_FOUND_EXCEPTION = new BaseException(ErrorCode.MEMBER_NOT_FOUND);

    @Override
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

    @Override
    public User findUser(String email) {
        return Optional.ofNullable(userRepository.findByEmailAndUseYn(email, Flag.Y))
                .orElseThrow(() -> MENEBER_NOT_FOUND_EXCEPTION);
    }

    @Override
    public User findUserById(long id) {
        return Optional.ofNullable(userRepository.findById(id))
                .orElseThrow(() -> MENEBER_NOT_FOUND_EXCEPTION);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = Optional.ofNullable(userRepository.findByEmailAndUseYn(email, Flag.Y))
                .orElseThrow(() -> MENEBER_NOT_FOUND_EXCEPTION);

        if (bCryptPasswordEncoder.matches(password, user.getPassword()) == false) {
            throw MENEBER_NOT_FOUND_EXCEPTION;
        }

        return user;
    }

    @Override
    @Transactional
    public User resignUser(long id) {
        User user = userRepository.findById(id);
        user.changeUseYn(Flag.N);

        return user;
    }

    @Override
    public boolean validateRegister(String email) {
        if(userRepository.countByEmail(email) > 0) {
            return false;
        }

        return true;
    }

    @Override
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

    @Override
    public ResponseCookie setCookie(TokenResponse tokenResponse) {
        return generateRefreshTokenCookie(tokenResponse.getRefreshTokenIndex());
    }

    @Override
    public ResponseCookie setRefreshTokenIndexCookie(long refreshTokenIndex) {
        return generateRefreshTokenCookie(refreshTokenIndex);
    }

    private ResponseCookie generateRefreshTokenCookie(long refreshTokenIndex) {
        return ResponseCookie.from(REFRESH_TOKEN_INDEX, String.valueOf(refreshTokenIndex))
                .httpOnly(true)
                .secure(true)
                .path("/")
                .secure(true)
                .build();

    }
}
