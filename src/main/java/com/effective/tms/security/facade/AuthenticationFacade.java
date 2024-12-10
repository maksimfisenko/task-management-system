package com.effective.tms.security.facade;

import com.effective.tms.security.web.model.AccessToken;
import com.effective.tms.security.web.model.LoginRequest;

public interface AuthenticationFacade {
    AccessToken authenticate(LoginRequest loginRequest);
}
