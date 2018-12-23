package com.company.boilerplate.services.auth;

import com.company.boilerplate.models.ApplicationUser;
import com.company.boilerplate.models.auth.JwtData;
import com.company.boilerplate.services.UserAccessService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static java.util.Collections.emptyMap;

@Service
public class AuthenticationService {
    private final UserAccessService userAccessService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtCreatorService jwtCreatorService;

    public AuthenticationService(UserAccessService userAccessService,
                                 BCryptPasswordEncoder bCryptPasswordEncoder,
                                 JwtCreatorService jwtCreatorService) {
        this.userAccessService = userAccessService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtCreatorService = jwtCreatorService;
    }

    public String authenticateUser(String username, String password) {
        ApplicationUser applicationUser = userAccessService.getUserByUsername(username);

        if(applicationUser == null || !bCryptPasswordEncoder.matches(password, applicationUser.getPassword())) return null;
        return jwtCreatorService.createJwtToken(new JwtData(username, "ADMIN", emptyMap()), LocalDateTime.now(ZoneId.of("UTC")));
    }
}
