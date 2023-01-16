package com.chalchal.chalchalserver.domain.auth.repository;

import com.chalchal.chalchalserver.domain.auth.entity.User;
import com.chalchal.chalchalserver.domain.auth.entity.UserJoinAuth;
import com.chalchal.chalchalserver.global.dto.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthRepository extends JpaRepository<UserJoinAuth, String> {
    List<UserJoinAuth> findAll();

    UserJoinAuth findOneById(long id);
    UserJoinAuth findTop1ByIdAndAuthYnOrderByRegDateDesc(Long id, Flag authYn);
}
