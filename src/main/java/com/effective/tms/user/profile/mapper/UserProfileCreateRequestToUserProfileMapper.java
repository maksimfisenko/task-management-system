package com.effective.tms.user.profile.mapper;

import com.effective.tms.security.mapper.Mapper;
import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.profile.web.model.UserProfileCreateRequest;

public interface UserProfileCreateRequestToUserProfileMapper
        extends Mapper<UserProfile, UserProfileCreateRequest> {
}
