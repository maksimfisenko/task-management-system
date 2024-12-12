package com.effective.tms.user.profile.api.service.impl;

import com.effective.tms.security.api.model.CurrentUserApiModel;
import com.effective.tms.security.api.service.IdentityApiService;
import com.effective.tms.user.profile.api.service.UserProfileApiService;
import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.profile.service.UserProfileService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileApiServiceImpl implements UserProfileApiService {

    private final IdentityApiService identityApiService;
    private final UserProfileService userProfileService;

    public UserProfileApiServiceImpl(IdentityApiService identityApiService, UserProfileService userProfileService) {
        this.identityApiService = identityApiService;
        this.userProfileService = userProfileService;
    }

    @Override
    public UserProfile getCurrentUserProfile() {
        CurrentUserApiModel currentUserApiModel = identityApiService.getCurrentUser()
                .orElseThrow(() -> new RuntimeException("User is not authorized"));
        return userProfileService.findUserById(currentUserApiModel.userAccountId())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserProfile getUserProfileById(Long id) {
        return userProfileService.findUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void createUserProfile(Long id, String username) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(id);
        userProfile.setUsername(username);
        userProfileService.createUserProfile(userProfile);
    }
}
