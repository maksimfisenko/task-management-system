package com.effective.tms.user.profile.service.impl;

import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.profile.repository.UserProfileRepository;
import com.effective.tms.user.profile.service.UserProfileService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void createUserProfile(UserProfile userProfile) {
        if (userProfileRepository.existsById(userProfile.getId())) {
            String errorMessage = String.format("User profile with this id = %d already exists",
                    userProfile.getId());
            throw new RuntimeException(errorMessage);
        }
        if (userProfileRepository.existsByUsername(userProfile.getUsername())) {
            String errorMessage = String.format("User profile with this nickname = %s already exists",
                    userProfile.getUsername());
            throw new RuntimeException(errorMessage);
        }
        userProfileRepository.save(userProfile);
    }
}
