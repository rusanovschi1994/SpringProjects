package com.rusanovschi.spring.springboot.auth;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ApplicationUserDAOImpl implements ApplicationUserDAO{
    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
        return Optional.empty();
    }
}
