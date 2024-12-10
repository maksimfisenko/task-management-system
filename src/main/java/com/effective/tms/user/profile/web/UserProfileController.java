package com.effective.tms.user.profile.web;

import com.effective.tms.user.profile.facade.UserProfileCreateFacade;
import com.effective.tms.user.profile.web.model.UserProfileCreateRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/profiles")
public class UserProfileController {

    private final UserProfileCreateFacade userProfileCreateFacade;

    public UserProfileController(UserProfileCreateFacade userProfileCreateFacade) {
        this.userProfileCreateFacade = userProfileCreateFacade;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserProfile(@Valid @RequestBody UserProfileCreateRequest createRequest) {
        log.info("Create profile {}", createRequest);
        userProfileCreateFacade.createUserProfile(createRequest);
    }
}
