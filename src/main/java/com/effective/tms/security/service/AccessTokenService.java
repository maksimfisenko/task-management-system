package com.effective.tms.security.service;

import org.springframework.security.core.Authentication;

public interface AccessTokenService {
    String generateAccessToken(Authentication authentication);
}
