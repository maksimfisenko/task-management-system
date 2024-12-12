package com.effective.tms.security.service.impl;

import com.effective.tms.security.model.UserAccount;
import com.effective.tms.security.repository.UserAccountRepository;
import com.effective.tms.security.service.UserAccountService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        if (userAccountRepository.existsByUsername(userAccount.getUsername())) {
            throw new RuntimeException("Account with this username already exists");
        }
        return userAccountRepository.save(userAccount);
    }

    @Override
    public Optional<UserAccount> findUserByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }
}
