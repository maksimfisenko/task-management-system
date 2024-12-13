package com.effective.tms.security.web;

import com.effective.tms.security.facade.AuthenticationFacade;
import com.effective.tms.security.web.model.AccessToken;
import com.effective.tms.security.web.model.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
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

    @PostMapping
    @Operation(
            summary = "Authenticate user by username and password",
            tags = {"Authentication"}
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    schema = @Schema(implementation = LoginRequest.class),
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
                    responseCode = "200",
                    description = "Successfully authenticated user",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "token": "eyJraWQiOiJmNGY3OGJiO..."
                                            }
                                            """
                            )
                    )
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
                                                "detail": "Bad credentials",
                                                "instance": "/api/v1/auth"
                                            }
                                            """
                            )
                    )
            ),
    })
    public AccessToken getToken(@Valid @RequestBody LoginRequest loginRequest) {
        return authenticationFacade.authenticate(loginRequest);
    }
}
