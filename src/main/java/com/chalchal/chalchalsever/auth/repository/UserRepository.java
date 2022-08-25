package com.chalchal.chalchalsever.auth.repository;

import com.chalchal.chalchalsever.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
