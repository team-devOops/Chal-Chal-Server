package com.chalchal.chalchalsever.domain.auth.service;

import com.chalchal.chalchalsever.domain.auth.entity.UserTokenInfo;
import com.chalchal.chalchalsever.domain.auth.dto.TokenResponse;

public interface UserTokenInfoService {
    UserTokenInfo createUserTokenInfo(TokenResponse tokenResponse);
    UserTokenInfo clearUserTokenInfo(TokenResponse tokenResponse);
    UserTokenInfo getTokenInfo(long refreshTokenIndex);
}
