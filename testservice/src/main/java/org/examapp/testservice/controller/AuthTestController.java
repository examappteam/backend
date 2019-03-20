package org.examapp.testservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthTestController {

    @GetMapping("/test")
    public ResponseEntity<?> testGet(){
        return ResponseEntity.ok("Test");
    }
}
