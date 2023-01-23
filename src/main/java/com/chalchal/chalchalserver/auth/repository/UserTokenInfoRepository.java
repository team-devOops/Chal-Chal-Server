package com.chalchal.chalchalserver.auth.repository;

import com.chalchal.chalchalserver.auth.domain.UserTokenInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenInfoRepository extends JpaRepository<UserTokenInfo, Long> {
    Optional<UserTokenInfo>  findByTokenIndex(long tokenIndex);

    long findIdByRefreshToken(String refreshToken);
}
