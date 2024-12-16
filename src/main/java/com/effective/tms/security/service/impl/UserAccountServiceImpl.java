package com.effective.tms.security.service.impl;

import com.effective.tms.common.exception.TmsException;
import com.effective.tms.security.model.UserAccount;
import com.effective.tms.security.repository.UserAccountRepository;
import com.effective.tms.security.service.UserAccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.effective.tms.common.constants.ServiceConstants.ACCOUNT_ALREADY_EXISTS;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        if (userAccountRepository.existsByUsername(userAccount.getUsername())) {
            throw new TmsException(String.format(ACCOUNT_ALREADY_EXISTS, userAccount.getUsername()));
        }
        return userAccountRepository.save(userAccount);
    }

    @Override
    public Optional<UserAccount> findUserByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }
}
