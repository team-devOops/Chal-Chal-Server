package com.chalchal.chalchalserver.domain.auth.service;

import com.chalchal.chalchalserver.domain.auth.entity.UserTokenInfo;
import com.chalchal.chalchalserver.domain.auth.dto.TokenResponse;

public interface UserTokenInfoService {
    UserTokenInfo createUserTokenInfo(TokenResponse tokenResponse);
    UserTokenInfo clearUserTokenInfo(TokenResponse tokenResponse);
    UserTokenInfo getTokenInfo(long refreshTokenIndex);
}
