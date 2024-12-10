package com.effective.tms.security.service.impl;

import com.effective.tms.security.service.AccessTokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    private final JwtEncoder jwtEncoder;

    public AccessTokenServiceImpl(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public String generateAccessToken(Authentication authentication) {
        UserDetails userDetails = Optional
                .of(authentication.getPrincipal())
                .filter(UserDetails.class::isInstance)
                .map(UserDetails.class::cast)
                .orElseThrow(() -> new RuntimeException("Failed to retrieve UserDetails object from Authentication"));

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        JwtClaimsSet claimsSet = JwtClaimsSet
                .builder()
                .claim("scope", roles)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                .subject(userDetails.getUsername())
                .build();

        return jwtEncoder
                .encode(JwtEncoderParameters.from(claimsSet))
                .getTokenValue();
    }
}
