package com.company.boilerplate.services.auth;

import com.company.boilerplate.models.ApplicationUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class LoginService  implements UserDetailsService {
    public LoginService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        ApplicationUser applicationUser = new ApplicationUser("ADMIN");
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}