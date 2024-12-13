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
    @Operation(
            security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Register user by username and password",
            tags = {"User Accounts"}
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    schema = @Schema(implementation = RegisterRequest.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(
                            value = """
                                    {
                                        "username": "test@gmail.com",
                                        "password": "test1234"
                                    }
                                    """
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully registered new user account"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Not valid request",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "type": "localhost:8080/api/v1",
                                                 "title": "Bad Request",
                                                 "status": 400,
                                                 "detail": "Account with this username already exists",
                                                 "instance": "/api/v1/accounts/register"
                                             }
                                            """
                            )
                    )
            ),
    })
    public void registerAccount(@Valid @RequestBody RegisterRequest registerRequest) {
        registerUserAccountFacade.register(registerRequest);
    }
}
