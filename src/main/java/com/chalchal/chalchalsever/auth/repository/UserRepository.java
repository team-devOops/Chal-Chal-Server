package com.chalchal.chalchalsever.auth.repository;

import com.chalchal.chalchalsever.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(String id);

    User findByIdAndPw(String id, String pw);
}
