package com.effective.tms.user.profile.facade.impl;

import com.effective.tms.user.profile.facade.UserProfileCreateFacade;
import com.effective.tms.user.profile.mapper.UserProfileCreateRequestToUserProfileMapper;
import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.profile.service.UserProfileService;
import com.effective.tms.user.profile.web.model.UserProfileCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class UserProfileCreateFacadeImpl implements UserProfileCreateFacade {

    private final UserProfileService userProfileService;
    private final UserProfileCreateRequestToUserProfileMapper userProfileCreateRequestToUserProfileMapper;

    public UserProfileCreateFacadeImpl(
            UserProfileService userProfileService,
            UserProfileCreateRequestToUserProfileMapper userProfileCreateRequestToUserProfileMapper) {
        this.userProfileService = userProfileService;
        this.userProfileCreateRequestToUserProfileMapper = userProfileCreateRequestToUserProfileMapper;
    }

    @Override
    public void createUserProfile(UserProfileCreateRequest createRequest) {
        UserProfile userProfile = userProfileCreateRequestToUserProfileMapper.map(createRequest);
        userProfileService.createUserProfile(userProfile);
    }
}
