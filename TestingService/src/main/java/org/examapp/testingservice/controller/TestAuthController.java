package org.examapp.testingservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAuthController {

    /**
     * Every endpoint can be protected by a role, this is done by the
     * PreAuthorize annotation, just add the prexix ROLE_ + the role name in all caps.
     *
     * To validate for multiple roles use the syntax below:
     * PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_TEACHER')")
     */
    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getTest(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return ResponseEntity.ok(username);
    }
}