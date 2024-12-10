package com.effective.tms.security.api.service.impl;

import com.effective.tms.security.api.model.CurrentUserApiModel;
import com.effective.tms.security.api.service.IdentityApiService;
import com.effective.tms.security.model.UserAccount;
import com.effective.tms.security.service.UserAccountService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdentityApiServiceImpl implements IdentityApiService {

    private final UserAccountService userAccountService;

    public IdentityApiServiceImpl(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
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
                .orElseThrow(() -> new RuntimeException("error"));

        return Optional.of(new CurrentUserApiModel(userAccount.getId(), username));
    }
}
