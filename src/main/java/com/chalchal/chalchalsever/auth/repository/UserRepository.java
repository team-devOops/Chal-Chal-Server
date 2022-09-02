package com.chalchal.chalchalsever.auth.repository;

import com.chalchal.chalchalsever.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findUserById(long id);

    User findByEmailAndPassword(String email, String pw);
}
