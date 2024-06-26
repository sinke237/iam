package org.sinke.oauth2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> home() {
        // return JSON response to React app
        return ResponseEntity.ok("{\"message\": \"Welcome to the home page. Please login.\"}");
    }

    @GetMapping("/failed-auth")
    public ResponseEntity<String> failedAuth() {
        // return JSON response to React app
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Authentication failed.\"}");
    }
}
