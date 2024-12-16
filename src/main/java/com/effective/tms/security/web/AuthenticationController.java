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

import static com.effective.tms.common.constants.ApiConstants.*;

@RestController
@RequestMapping(AUTH_MAPPING)
public class AuthenticationController {

    private final AuthenticationFacade authenticationFacade;


    public AuthenticationController(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @PostMapping
    @Operation(
            summary = AUTH_SUMMARY,
            tags = {AUTH_TAG}
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    schema = @Schema(implementation = LoginRequest.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(value = AUTH_REQUEST_BODY)
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = STATUS_OK,
                    description = AUTH_DESC_OK,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = AUTH_RESPONSE_BODY_OK)
                    )
            ),
            @ApiResponse(
                    responseCode = STATUS_BAD_REQUEST,
                    description = AUTH_DESC_BAD_REQUEST,
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = AUTH_RESPONSE_BODY_BAD_REQUEST)
                    )
            ),
    })
    public AccessToken getToken(@Valid @RequestBody LoginRequest loginRequest) {
        return authenticationFacade.authenticate(loginRequest);
    }
}
