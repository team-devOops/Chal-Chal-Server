package com.chalchal.chalchalsever.domain.auth.repository;

import com.chalchal.chalchalsever.domain.auth.entity.UserJoinAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserJoinAuth, Long> {

}
