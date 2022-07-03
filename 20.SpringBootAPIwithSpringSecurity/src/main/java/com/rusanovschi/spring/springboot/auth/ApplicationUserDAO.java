package com.rusanovschi.spring.springboot.auth;


import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ApplicationUserDAO {

    Optional<ApplicationUser> selectApplicationUserByUserName (String username);
}
