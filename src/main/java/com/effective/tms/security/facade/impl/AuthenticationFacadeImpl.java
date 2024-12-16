package com.effective.tms.security.facade.impl;

import com.effective.tms.security.facade.AuthenticationFacade;
import com.effective.tms.security.service.AccessTokenService;
import com.effective.tms.security.web.model.AccessToken;
import com.effective.tms.security.web.model.LoginRequest;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Transactional
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final AuthenticationManager authenticationManager;
    private final AccessTokenService accessTokenService;

    public AuthenticationFacadeImpl(AuthenticationManager authenticationManager,
                                    AccessTokenService accessTokenService) {
        this.authenticationManager = authenticationManager;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public AccessToken authenticate(LoginRequest loginRequest) {
        Authentication token = new UsernamePasswordAuthenticationToken(
                loginRequest.username().toLowerCase(Locale.ROOT),
                loginRequest.password()
        );

        Authentication authentication = authenticationManager.authenticate(token);

        return new AccessToken(accessTokenService.generateAccessToken(authentication));
    }
}
