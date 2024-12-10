package com.effective.tms.security.service.impl;

import com.effective.tms.security.model.UserAccount;
import com.effective.tms.security.repository.UserAccountRepository;
import com.effective.tms.security.service.UserAccountService;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public void createUserAccount(UserAccount userAccount) {
        if (userAccountRepository.existsByUsername(userAccount.getUsername())) {
            throw new RuntimeException("Account with this username already exists");
        }
        userAccountRepository.save(userAccount);
    }
}
