package com.effective.tms.security.web;

import com.effective.tms.security.facade.AuthenticationFacade;
import com.effective.tms.security.web.model.AccessToken;
import com.effective.tms.security.web.model.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationFacade authenticationFacade;


    public AuthenticationController(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @PostMapping("/token")
    public AccessToken getToken(@Valid @RequestBody LoginRequest loginRequest) {
        return authenticationFacade.authenticate(loginRequest);
    }
}
