package com.chalchal.chalchalsever.auth.service;

import com.chalchal.chalchalsever.domain.UserTokenInfo;
import com.chalchal.chalchalsever.dto.TokenResponse;

public interface UserTokenInfoService {
    UserTokenInfo createUserTokenInfo(TokenResponse tokenResponse);
    UserTokenInfo getTokenInfo(long refreshTokenIndex);
}
