package com.chalchal.chalchalsever.auth.service;

import com.chalchal.chalchalsever.auth.repository.UserTokenInfoRepository;
import com.chalchal.chalchalsever.domain.UserTokenInfo;
import com.chalchal.chalchalsever.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTokenInfoServiceImpl implements UserTokenInfoService {
    private final UserTokenInfoRepository userTokenInfoRepository;

    @Override
    public UserTokenInfo createUserTokenInfo(TokenResponse tokenResponse) {
        return userTokenInfoRepository.save(UserTokenInfo.builder()
                .id(tokenResponse.getId())
                .refreshToken(tokenResponse.getREFRESH_TOKEN())
                .build());
    }
}
