package com.company.boilerplate.services.auth;

import com.company.boilerplate.services.UserAccessService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserAccessService userAccessService;

    public AuthenticationService(UserAccessService userAccessService) {
        this.userAccessService = userAccessService;
    }

    public String authenticateUser(String username, String password) {

        return null;
    }
}
