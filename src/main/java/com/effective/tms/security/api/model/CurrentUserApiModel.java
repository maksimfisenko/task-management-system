package com.effective.tms.security.api.model;

import com.effective.tms.security.model.UserRole;

import java.util.Set;

public record CurrentUserApiModel(
        Long userAccountId,
        String username,
        Set<UserRole> authorities
) {
}
