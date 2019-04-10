package org.examapp.authentication.config.jwt;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {
    InvalidJwtAuthenticationException(String e) {
        super(e);
    }
}
