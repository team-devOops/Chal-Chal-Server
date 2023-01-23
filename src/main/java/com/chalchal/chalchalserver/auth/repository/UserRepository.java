package com.chalchal.chalchalserver.auth.repository;

import com.chalchal.chalchalserver.auth.domain.User;
import com.chalchal.chalchalserver.global.dto.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndUseYn(String email, Flag useYn);
    Optional<User> findById(long id);

    long countByEmail(String email);
}
