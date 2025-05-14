package com.oleg.customer.costs.analytics.security;

import com.oleg.customer.costs.analytics.user.UserContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class UserContextProvider {

    public UserContext getUserContext() {
        var auth = getContext().getAuthentication();
        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            return (UserContext) jwtAuth.getDetails();
        } else {
            throw new BadCredentialsException("Token not found!");
        }
    }
}
