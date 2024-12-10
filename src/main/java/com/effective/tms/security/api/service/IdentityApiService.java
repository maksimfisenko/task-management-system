package com.effective.tms.security.api.service;

import com.effective.tms.security.api.model.CurrentUserApiModel;

import java.util.Optional;

public interface IdentityApiService {
    Optional<CurrentUserApiModel> getCurrentUser();
}
