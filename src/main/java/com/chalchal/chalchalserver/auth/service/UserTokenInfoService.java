package com.chalchal.chalchalserver.auth.service;

import com.chalchal.chalchalserver.auth.repository.UserTokenInfoRepository;
import com.chalchal.chalchalserver.auth.domain.UserTokenInfo;
import com.chalchal.chalchalserver.auth.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.chalchal.chalchalserver.global.exception.BaseException.RESOURCE_NOT_FOUND_EXCEPTION;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTokenInfoService {
    private final UserTokenInfoRepository userTokenInfoRepository;

    public UserTokenInfo createUserTokenInfo(TokenResponse tokenResponse) {
        return userTokenInfoRepository.save(UserTokenInfo.builder()
                .id(tokenResponse.getId())
                .refreshToken(tokenResponse.getRefreshToken())
                .build());
    }

    public UserTokenInfo clearUserTokenInfo(TokenResponse tokenResponse) {
        return userTokenInfoRepository.save(UserTokenInfo.builder()
                .id(tokenResponse.getId())
                .tokenIndex(tokenResponse.getRefreshTokenIndex())
                .refreshToken(tokenResponse.getRefreshToken())
                .build());
    }

    public UserTokenInfo getTokenInfo(long tokenIndex) {
        return userTokenInfoRepository.findByTokenIndex(tokenIndex)
                .orElseThrow(() -> RESOURCE_NOT_FOUND_EXCEPTION);
    }
}
