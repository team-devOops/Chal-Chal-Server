package com.chalchal.chalchalserver.auth.repository;

import com.chalchal.chalchalserver.auth.domain.UserProfileImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileImgRepository extends JpaRepository<UserProfileImg, String> {
    int countById(Long id);
}