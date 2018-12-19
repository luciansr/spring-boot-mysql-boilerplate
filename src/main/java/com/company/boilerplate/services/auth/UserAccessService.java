package com.company.boilerplate.services.auth;

import com.company.boilerplate.models.ApplicationUser;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserAccessService {
    public ApplicationUser getUserByName(String username) {
        return new ApplicationUser("ADMIN");
    }
}
