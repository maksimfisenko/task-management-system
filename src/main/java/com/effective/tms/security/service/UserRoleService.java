package com.effective.tms.security.service;

import com.effective.tms.security.model.UserRole;

import java.util.Optional;

public interface UserRoleService {
    Optional<UserRole> findUserRole();
}
