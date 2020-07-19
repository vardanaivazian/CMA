package com.sflpro.cma.service.security;

/**
 * Auto login not used in this app
 */

public interface SecurityService {

    String findLoggedInEmail();

    void login( String email, String password);

}
