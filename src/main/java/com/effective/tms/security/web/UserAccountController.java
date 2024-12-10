package com.effective.tms.security.web;

import com.effective.tms.security.model.UserAccount;
import com.effective.tms.security.model.UserRole;
import com.effective.tms.security.service.UserAccountService;
import com.effective.tms.security.service.UserRoleService;
import com.effective.tms.security.web.model.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
public class UserAccountController {

    private final UserAccountService userAccountService;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    public UserAccountController(UserAccountService userAccountService, UserRoleService userRoleService, PasswordEncoder passwordEncoder) {
        this.userAccountService = userAccountService;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@RequestBody RegisterRequest registerRequest) {
        log.info("Register account {}", registerRequest);

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(registerRequest.username().toLowerCase(Locale.ROOT));
        userAccount.setPassword(passwordEncoder.encode(registerRequest.password()));
        UserRole userRole = userRoleService.findUserRole().orElseThrow(() -> new RuntimeException("Failed to find user role"));
        userAccount.setAuthorities(Set.of(userRole));

        userAccountService.createUserAccount(userAccount);

    }
}
