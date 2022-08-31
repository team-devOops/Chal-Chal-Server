package com.chalchal.chalchalsever.auth.repository;

import com.chalchal.chalchalsever.domain.UserTokenInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenInfoRepository extends JpaRepository<UserTokenInfo, Long> {
    boolean existsByRefreshToken(String token);
}
