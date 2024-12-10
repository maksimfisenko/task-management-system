package com.effective.tms.security.service.impl;

import com.effective.tms.security.model.UserRole;
import com.effective.tms.security.repository.UserRoleRepository;
import com.effective.tms.security.service.UserAccountService;
import com.effective.tms.security.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public Optional<UserRole> findUserRole() {
        return userRoleRepository.findByAuthority("ROLE_USER");
    }
}
