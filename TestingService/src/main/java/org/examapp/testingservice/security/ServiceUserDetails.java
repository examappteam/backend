package org.examapp.testingservice.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * This is an implementation of the UserDetails interface.
 *
 * The reason this should be done is because spring security needs an implementation of this interface
 * to add the authentication roles from the JWT to the SecuriyContext
 *
 * In this class we only define a username and authorities field, because we only need a username and roles
 * At this moment we don't check if accounts may be locked or expired, so the values of those are hardcoded.
 */
public class ServiceUserDetails implements UserDetails {

    private String username;

    private Collection<? extends GrantedAuthority> authorities;


    public ServiceUserDetails(String username, String[] authorities) {
        this.username = username;
        this.authorities = AuthorityUtils.createAuthorityList(authorities);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
