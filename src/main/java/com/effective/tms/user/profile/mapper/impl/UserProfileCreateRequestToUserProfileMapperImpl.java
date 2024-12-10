package com.effective.tms.user.profile.mapper.impl;

import com.effective.tms.security.api.model.CurrentUserApiModel;
import com.effective.tms.security.api.service.IdentityApiService;
import com.effective.tms.user.profile.mapper.UserProfileCreateRequestToUserProfileMapper;
import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.profile.web.model.UserProfileCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class UserProfileCreateRequestToUserProfileMapperImpl
        implements UserProfileCreateRequestToUserProfileMapper {

    private final IdentityApiService identityApiService;

    public UserProfileCreateRequestToUserProfileMapperImpl(IdentityApiService identityApiService) {
        this.identityApiService = identityApiService;
    }

    @Override
    public UserProfile map(UserProfileCreateRequest createRequest) {

        CurrentUserApiModel currentUserApiModel = identityApiService
                .getCurrentUser()
                .orElseThrow(() -> new RuntimeException("User not authorized"));

        UserProfile userProfile = new UserProfile();
        userProfile.setId(currentUserApiModel.userAccountId());
        userProfile.setUsername(currentUserApiModel.username());
        return userProfile;
    }
}
