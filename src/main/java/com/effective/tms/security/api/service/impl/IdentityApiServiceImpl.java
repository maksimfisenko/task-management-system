package com.effective.tms.security.api.service.impl;

import com.effective.tms.security.api.model.CurrentUserApiModel;
import com.effective.tms.security.api.service.IdentityApiService;
import com.effective.tms.security.model.UserAccount;
import com.effective.tms.security.model.UserRole;
import com.effective.tms.security.service.UserAccountService;
import com.effective.tms.security.service.UserRoleService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class IdentityApiServiceImpl implements IdentityApiService {

    private final UserAccountService userAccountService;
    private final UserRoleService userRoleService;

    public IdentityApiServiceImpl(UserAccountService userAccountService,
                                  UserRoleService userRoleService) {
        this.userAccountService = userAccountService;
        this.userRoleService = userRoleService;
    }

    @Override
    public Optional<CurrentUserApiModel> getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }
        String username = authentication.getName();

        UserAccount userAccount = userAccountService
                .findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User account not found"));

        return Optional.of(new CurrentUserApiModel(userAccount.getId(), username, userAccount.getAuthorities()));
    }

    @Override
    public UserRole getAdminRole() {
        return userRoleService
                .findAdminRole()
                .orElseThrow(() -> new RuntimeException("Admin role not found"));
    }
}
