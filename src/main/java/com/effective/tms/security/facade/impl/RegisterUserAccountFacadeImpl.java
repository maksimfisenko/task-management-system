package com.effective.tms.security.facade.impl;

import com.effective.tms.security.facade.RegisterUserAccountFacade;
import com.effective.tms.security.mapper.RegisterRequestToUserAccountMapper;
import com.effective.tms.security.model.UserAccount;
import com.effective.tms.security.service.UserAccountService;
import com.effective.tms.security.web.model.RegisterRequest;
import com.effective.tms.user.profile.api.service.UserProfileApiService;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserAccountFacadeImpl implements RegisterUserAccountFacade {

    private final UserProfileApiService userProfileApiService;
    private final UserAccountService userAccountService;
    private final RegisterRequestToUserAccountMapper registerRequestToUserAccountMapper;

    public RegisterUserAccountFacadeImpl(UserProfileApiService userProfileApiService,
                                         UserAccountService userAccountService,
                                         RegisterRequestToUserAccountMapper registerRequestToUserAccountMapper) {
        this.userProfileApiService = userProfileApiService;
        this.userAccountService = userAccountService;
        this.registerRequestToUserAccountMapper = registerRequestToUserAccountMapper;
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        UserAccount userAccount = registerRequestToUserAccountMapper.map(registerRequest);
        UserAccount createdAccount = userAccountService.createUserAccount(userAccount);

        userProfileApiService.createUserProfile(createdAccount.getId(), createdAccount.getUsername());
    }
}
