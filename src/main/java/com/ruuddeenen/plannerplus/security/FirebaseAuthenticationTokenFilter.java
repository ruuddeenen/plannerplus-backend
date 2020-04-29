package com.ruuddeenen.plannerplus.security;

import com.google.api.client.util.Strings;
import com.ruuddeenen.plannerplus.security.models.FirebaseAuthToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirebaseAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    private final static String TOKEN_HEADER = "X-Firebase-Auth";

    public FirebaseAuthenticationTokenFilter() {
        super("/**");
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        final String authToken = request.getHeader(TOKEN_HEADER);
        if (Strings.isNullOrEmpty(authToken)){
            throw new RuntimeException("Invalid auth token!");
        }
        return getAuthenticationManager().authenticate(new FirebaseAuthToken(authToken));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
