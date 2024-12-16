package com.effective.tms.security.service.impl;

import com.effective.tms.security.model.UserRole;
import com.effective.tms.security.repository.UserRoleRepository;
import com.effective.tms.security.service.UserRoleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.effective.tms.common.constants.ServiceConstants.ROLE_ADMIN;
import static com.effective.tms.common.constants.ServiceConstants.ROLE_USER;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public Optional<UserRole> findUserRole() {
        return userRoleRepository.findByAuthority(ROLE_USER);
    }

    @Override
    public Optional<UserRole> findAdminRole() {
        return userRoleRepository.findByAuthority(ROLE_ADMIN);
    }
}
