package com.effective.tms.security.mapper.impl;

import com.effective.tms.security.mapper.RegisterRequestToUserAccountMapper;
import com.effective.tms.security.mapper.UserAccountToUserMapper;
import com.effective.tms.security.model.UserAccount;
import com.effective.tms.security.model.UserRole;
import com.effective.tms.security.service.UserRoleService;
import com.effective.tms.security.web.model.RegisterRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Set;

@Component
public class UserAccountToUserMapperImpl implements UserAccountToUserMapper {
    @Override
    public User map(UserAccount userAccount) {
        return new User(
                userAccount.getUsername(),
                userAccount.getPassword(),
                userAccount.getAuthorities());
    }
}
