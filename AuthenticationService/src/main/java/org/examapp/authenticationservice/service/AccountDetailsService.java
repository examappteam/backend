package org.examapp.authenticationservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
