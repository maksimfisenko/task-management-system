package com.effective.tms.security.api.service;

import com.effective.tms.security.api.model.CurrentUserApiModel;
import com.effective.tms.security.model.UserRole;

import java.util.Optional;

public interface IdentityApiService {
    Optional<CurrentUserApiModel> getCurrentUser();
    UserRole getAdminRole();
}
