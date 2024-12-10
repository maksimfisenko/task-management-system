package com.effective.tms.security.facade.impl;

import com.effective.tms.security.facade.RegisterUserAccountFacade;
import com.effective.tms.security.mapper.RegisterRequestToUserAccountMapper;
import com.effective.tms.security.model.UserAccount;
import com.effective.tms.security.service.UserAccountService;
import com.effective.tms.security.web.model.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserAccountFacadeImpl implements RegisterUserAccountFacade {

    private final UserAccountService userAccountService;
    private final RegisterRequestToUserAccountMapper registerRequestToUserAccountMapper;

    public RegisterUserAccountFacadeImpl(UserAccountService userAccountService,
                                         RegisterRequestToUserAccountMapper registerRequestToUserAccountMapper) {
        this.userAccountService = userAccountService;
        this.registerRequestToUserAccountMapper = registerRequestToUserAccountMapper;
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        UserAccount userAccount = registerRequestToUserAccountMapper.map(registerRequest);
        userAccountService.createUserAccount(userAccount);
    }
}
