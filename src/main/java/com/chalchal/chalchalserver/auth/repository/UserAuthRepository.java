package com.chalchal.chalchalserver.auth.repository;

import com.chalchal.chalchalserver.auth.domain.UserJoinAuth;
import com.chalchal.chalchalserver.global.dto.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserAuthRepository extends JpaRepository<UserJoinAuth, String> {
    List<UserJoinAuth> findAll();

    UserJoinAuth findOneById(long id);
    UserJoinAuth findTop1ByIdAndAuthYnAndValidDateAfterOrderByRegDateDesc(Long id, Flag authYn, LocalDateTime now);
}
