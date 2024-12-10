package com.effective.tms.security.web;

import com.effective.tms.security.facade.RegisterUserAccountFacade;
import com.effective.tms.security.web.model.RegisterRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
public class UserAccountController {

    private final RegisterUserAccountFacade registerUserAccountFacade;

    public UserAccountController(RegisterUserAccountFacade registerUserAccountFacade) {
        this.registerUserAccountFacade = registerUserAccountFacade;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody RegisterRequest registerRequest) {
        log.info("Register account {}", registerRequest);
        registerUserAccountFacade.register(registerRequest);
    }
}
