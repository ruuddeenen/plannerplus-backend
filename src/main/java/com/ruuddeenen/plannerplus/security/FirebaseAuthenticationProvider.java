package com.ruuddeenen.plannerplus.security;

import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.ruuddeenen.plannerplus.security.models.FirebaseAuthToken;
import com.ruuddeenen.plannerplus.security.models.FirebaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class FirebaseAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Override
    public boolean supports(Class<?> authentication) {
        return (FirebaseAuthToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        final FirebaseAuthToken authenticationToken = (FirebaseAuthToken) usernamePasswordAuthenticationToken;

        ApiFuture<FirebaseToken> task = firebaseAuth.verifyIdTokenAsync(authenticationToken.getToken());
        try {
            FirebaseToken token = task.get();
            return new FirebaseUserDetails(token.getEmail(), token.getUid());
        } catch (InterruptedException | ExecutionException e) {
            throw new SessionAuthenticationException(e.getMessage());
        }

    }
}
