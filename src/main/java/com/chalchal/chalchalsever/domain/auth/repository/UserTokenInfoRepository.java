package com.chalchal.chalchalsever.domain.auth.repository;

import com.chalchal.chalchalsever.domain.auth.entity.UserTokenInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenInfoRepository extends JpaRepository<UserTokenInfo, Long> {
    UserTokenInfo findByTokenIndex(long tokenIndex);

    long findIdByRefreshToken(String refreshToken);
}
