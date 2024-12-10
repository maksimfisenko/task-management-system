package com.effective.tms.user.profile.facade;

import com.effective.tms.user.profile.web.model.UserProfileCreateRequest;

public interface UserProfileCreateFacade {
    void createUserProfile(UserProfileCreateRequest createRequest);
}
