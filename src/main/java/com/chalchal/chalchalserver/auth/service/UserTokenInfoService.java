package com.chalchal.chalchalserver.auth.service;

import com.chalchal.chalchalserver.auth.domain.UserTokenInfo;
import com.chalchal.chalchalserver.auth.dto.TokenResponse;

public interface UserTokenInfoService {
    UserTokenInfo createUserTokenInfo(TokenResponse tokenResponse);
    UserTokenInfo clearUserTokenInfo(TokenResponse tokenResponse);
    UserTokenInfo getTokenInfo(long refreshTokenIndex);
}
