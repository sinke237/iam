package org.sinke.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/sinke-resources")
    public String getSinkeResources() {
        return "This is a protected resource. Only authenticated users can access this.";
    }
}
