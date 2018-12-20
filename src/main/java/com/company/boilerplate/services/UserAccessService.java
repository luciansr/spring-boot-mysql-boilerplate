package com.company.boilerplate.services;

import com.company.boilerplate.models.ApplicationUser;
import org.springframework.stereotype.Service;

@Service
public class UserAccessService {
    public ApplicationUser getUserByUsername(String username) {
        return new ApplicationUser("ADMIN");
    }
}
