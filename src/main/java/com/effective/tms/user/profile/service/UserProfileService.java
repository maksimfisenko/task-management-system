package com.effective.tms.user.profile.service;

import com.effective.tms.user.profile.model.UserProfile;

import java.util.Optional;

public interface UserProfileService {
    void createUserProfile(UserProfile userProfile);
    Optional<UserProfile> findUserById(Long id);
}
