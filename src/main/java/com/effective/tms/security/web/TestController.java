package com.effective.tms.security.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class TestController {

    @GetMapping("/auth")
    public String hitAuthEndpoint() {
        return "Protected resource";
    }

    @GetMapping("/user")
    public String hitRoleUserEndpoint() {
        return "Works for ROLE_USER";
    }

    @GetMapping("/admin")
    public String hitRoleAdminEndpoint() {
        return "Works for ROLE_ADMIN";
    }

}