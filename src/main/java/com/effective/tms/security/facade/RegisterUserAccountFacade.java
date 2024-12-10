package com.effective.tms.security.facade;

import com.effective.tms.security.web.model.RegisterRequest;

public interface RegisterUserAccountFacade {
    void register(RegisterRequest registerRequest);
}
