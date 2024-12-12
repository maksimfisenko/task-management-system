package com.effective.tms.security.service;

import com.effective.tms.security.model.UserAccount;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserAccountService {
    UserAccount createUserAccount(UserAccount userAccount);
    Optional<UserAccount> findUserByUsername(String username);
}
