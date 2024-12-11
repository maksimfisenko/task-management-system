package com.effective.tms.user.profile.api.service;

import com.effective.tms.user.profile.model.UserProfile;

public interface UserProfileApiService {
    UserProfile getCurrentUserProfile();
    UserProfile getUserProfileById(Long id);
}
