package org.sinke.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/api/resource")
    public String getResource() {
        return "This is a protected resource";
    }
}
