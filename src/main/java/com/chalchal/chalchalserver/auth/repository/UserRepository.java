package com.chalchal.chalchalserver.auth.repository;

import com.chalchal.chalchalserver.auth.domain.User;
import com.chalchal.chalchalserver.global.dto.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndUseYn(String email, Flag useYn);
    User findById(long id);

    Long countByEmail(String email);
}
