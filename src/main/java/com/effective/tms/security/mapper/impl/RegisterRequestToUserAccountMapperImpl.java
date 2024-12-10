package com.effective.tms.security.mapper.impl;

import com.effective.tms.security.mapper.RegisterRequestToUserAccountMapper;
import com.effective.tms.security.model.UserAccount;
import com.effective.tms.security.model.UserRole;
import com.effective.tms.security.service.UserRoleService;
import com.effective.tms.security.web.model.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Set;

@Component
public class RegisterRequestToUserAccountMapperImpl implements RegisterRequestToUserAccountMapper {

    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;

    public RegisterRequestToUserAccountMapperImpl(PasswordEncoder passwordEncoder, UserRoleService userRoleService) {
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserAccount map(RegisterRequest registerRequest) {
        UserRole userRole = userRoleService
                .findUserRole()
                .orElseThrow(
                        () -> new RuntimeException("Failed to find user role"));

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(registerRequest.username().toLowerCase(Locale.ROOT));
        userAccount.setPassword(passwordEncoder.encode(registerRequest.password()));
        userAccount.setAuthorities(Set.of(userRole));

        return userAccount;
    }
}
