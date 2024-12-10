package com.effective.tms.user.profile.repository;

import com.effective.tms.user.profile.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByUsername(String username);
}
