package com.effective.tms.security.service;

import com.effective.tms.security.model.UserAccount;

import java.util.Optional;

public interface UserAccountService {
    UserAccount createUserAccount(UserAccount userAccount);
    Optional<UserAccount> findUserByUsername(String username);
}
