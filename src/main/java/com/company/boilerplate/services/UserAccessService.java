package com.company.boilerplate.services;

import com.company.boilerplate.models.ApplicationUser;
import org.springframework.stereotype.Service;

@Service
public class UserAccessService {


    public ApplicationUser getUserByUsername(String username) {
        //password=ADMIN
        final String password = "$2a$04$M6.mOR6sAutIS2RRHhs3XOI9i0MnkSJPYs0MfTQy.FvXFZj/9616a";
        return new ApplicationUser(username, password);
    }
}
