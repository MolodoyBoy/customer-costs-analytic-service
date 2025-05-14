package com.oleg.customer.costs.analytics.security;

import com.oleg.customer.costs.analytics.user.UserContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

import static java.lang.Integer.parseInt;

public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        int userId = parseInt(jwt.getClaim("userId"));
        String username = jwt.getClaim("username");

        UserContext userContext = new UserContext(
            userId,
            username
        );

        JwtAuthenticationToken token = new JwtAuthenticationToken(jwt, List.of(), userContext.username());
        token.setDetails(userContext);

        return token;
    }
}
