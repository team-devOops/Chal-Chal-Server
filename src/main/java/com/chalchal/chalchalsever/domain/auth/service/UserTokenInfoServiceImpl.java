package com.chalchal.chalchalsever.domain.auth.service;

import com.chalchal.chalchalsever.domain.auth.repository.UserTokenInfoRepository;
import com.chalchal.chalchalsever.domain.auth.entity.UserTokenInfo;
import com.chalchal.chalchalsever.domain.auth.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTokenInfoServiceImpl implements UserTokenInfoService {
    private final UserTokenInfoRepository userTokenInfoRepository;

    @Override
    public UserTokenInfo createUserTokenInfo(TokenResponse tokenResponse) {
        return userTokenInfoRepository.save(UserTokenInfo.builder()
                .id(tokenResponse.getId())
                .refreshToken(tokenResponse.getRefreshToken())
                .build());
    }

    @Override
    public UserTokenInfo clearUserTokenInfo(TokenResponse tokenResponse) {
        return userTokenInfoRepository.save(UserTokenInfo.builder()
                .id(tokenResponse.getId())
                .tokenIndex(tokenResponse.getRefreshTokenIndex())
                .refreshToken(tokenResponse.getRefreshToken())
                .build());
    }

    @Override
    public UserTokenInfo getTokenInfo(long tokenIndex) {
        return userTokenInfoRepository.findByTokenIndex(tokenIndex);
    }
}
