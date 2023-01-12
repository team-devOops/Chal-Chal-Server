package com.chalchal.chalchalsever.domain.auth.repository;

import com.chalchal.chalchalsever.domain.auth.entity.User;
import com.chalchal.chalchalsever.domain.auth.entity.UserJoinAuth;
import com.chalchal.chalchalsever.global.dto.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthRepository extends JpaRepository<UserJoinAuth, String> {
    List<UserJoinAuth> findAll();

    UserJoinAuth findOneById(long id);
    UserJoinAuth findTop1ByIdAndAuthYnOrderByRegDateDesc(Long id, Flag authYn);
}
