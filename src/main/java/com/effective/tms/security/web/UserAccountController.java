package com.effective.tms.security.web;

import com.effective.tms.security.facade.RegisterUserAccountFacade;
import com.effective.tms.security.web.model.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import static com.effective.tms.common.constants.ApiConstants.*;

@Slf4j
@RestController
@RequestMapping(ACCOUNTS_MAPPING)
public class UserAccountController {

    private final RegisterUserAccountFacade registerUserAccountFacade;

    public UserAccountController(RegisterUserAccountFacade registerUserAccountFacade) {
        this.registerUserAccountFacade = registerUserAccountFacade;
    }

    @PostMapping(ACCOUNT_REGISTER_MAPPING)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            security = {@SecurityRequirement(name = BEARER_KEY)},
            summary = REGISTER_ACCOUNT_SUMMARY,
            tags = {ACCOUNTS_TAG}
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    schema = @Schema(implementation = RegisterRequest.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(value = REGISTER_ACCOUNT_REQUEST_BODY)
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = STATUS_CREATED,
                    description = REGISTER_ACCOUNT_DESC_CREATED
            ),
            @ApiResponse(
                    responseCode = STATUS_BAD_REQUEST,
                    description = REGISTER_ACCOUNT_DESC_BAD_REQUEST,
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = REGISTER_ACCOUNT_RESPONSE_BODY_BAD_REQUEST)
                    )
            ),
    })
    public void registerAccount(@Valid @RequestBody RegisterRequest registerRequest) {
        registerUserAccountFacade.register(registerRequest);
    }
}
