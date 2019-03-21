package com.example.notification.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * This class is the entry point for filtering any http request in spring
 * Here we can configure things like, accept CORS or apply custom filters
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ServiceJwtTokenFilterConfigurer filterConfigurer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Allow CORS and disable csrf
        http.cors().and().csrf().disable();

        // Make sure spring security doesn't create any session as it's not needed
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry point for this service
        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll()
            .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();


        // Apply the filter to filter the jwt from the header
        http.apply(filterConfigurer);
    }
}
