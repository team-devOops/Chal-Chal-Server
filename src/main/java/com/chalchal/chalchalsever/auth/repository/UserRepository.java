package com.chalchal.chalchalsever.auth.repository;

import com.chalchal.chalchalsever.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndUseYn(String email, String useYn);
    User findById(long id);

    Long countByEmail(String email);
}
