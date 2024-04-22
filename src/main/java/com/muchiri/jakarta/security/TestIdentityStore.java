package com.muchiri.jakarta.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import java.util.Set;

/**
 *
 * @author muchiri
 */
//@ApplicationScoped
public class TestIdentityStore implements IdentityStore {

//    public CredentialValidationResult validate(UsernamePasswordCredential credentials) {
//        if (credentials.compareTo("john", "password")) {
//            return new CredentialValidationResult("john", Set.of("user", "caller"));
//        }
//
//        return CredentialValidationResult.INVALID_RESULT;
//    }

}
