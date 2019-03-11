package com.example.zuulgateway.security;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    Gson gson;
    Logger LOGGER_ = Logger.getLogger(this.getClass().getName());

    public TokenAuthenticationFilter() {
        gson = new Gson();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 1. get the authentication header. Tokens are supposed to be passed in the authentication header
        String header = request.getHeader("Authorization");


        // 2. validate the header and check the prefix
        if(header == null || !header.startsWith("Bearer ")){
            chain.doFilter(request, response);  		// If not valid, go to the next filter.
            return;
        }

        // If there is no token provided and hence the user won't be authenticated.
        // It's Ok. Maybe the user accessing a public path or asking for a token.

        // All secured paths that needs a token are already defined and secured in config class.
        // And If user tried to access without access token, then he won't be authenticated and an exception will be thrown.

        // 3. Get the token
        String token = header.replace("Bearer ", "");

        String query = "http://localhost:22502/verify";
        HttpPost httpPost = new HttpPost(query);
        String username = new String();
        String test = gson.toJson(token);
        StringEntity params = new StringEntity(test, ContentType.APPLICATION_JSON);
        httpPost.setEntity(params);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse verifyResponse = httpClient.execute(httpPost)) {

            HttpEntity entity = verifyResponse.getEntity();
            final String entityString = EntityUtils.toString(entity);

            username = gson.fromJson(entityString, String.class);

        } catch (IOException e) {
            LOGGER_.log(Level.SEVERE, e.toString(), e);
        }

        try {	// exceptions might be thrown in creating the claims if for example the token is expired

            // 4. Validate the token
            if(!username.equals("")) {
                @SuppressWarnings("unchecked")

                // 5. Create auth object
                // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated securityUser.
                // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
                String getuserquery = "http://localhost:22502/" + username;
                HttpGet httpGet = new HttpGet(getuserquery);
                SecurityUser securityUser = null;
                try (CloseableHttpClient httpClient = HttpClients.createDefault();
                     CloseableHttpResponse verifyResponse = httpClient.execute(httpGet)) {

                    HttpEntity entity = verifyResponse.getEntity();
                    final String entityString = EntityUtils.toString(entity);

                    securityUser = gson.fromJson(entityString, SecurityUser.class);
                    securityUser.token = token;

                } catch (IOException e) {
                    LOGGER_.log(Level.SEVERE, e.toString(), e);
                }

                // https://stackoverflow.com/a/51929117/10524573
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, securityUser.getAuthorities());

                // 6. Authenticate the securityUser
                // Now, securityUser is authenticated
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            // In case of failure. Make sure it's clear; so guarantee user won't be authenticated
            SecurityContextHolder.clearContext();
        }

        // go to the next filter in the filter chain
        chain.doFilter(request, response);
    }
}