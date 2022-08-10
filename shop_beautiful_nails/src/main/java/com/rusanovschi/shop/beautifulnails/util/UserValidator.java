package com.rusanovschi.shop.beautifulnails.util;

import com.rusanovschi.shop.beautifulnails.entity.User;
import com.rusanovschi.shop.beautifulnails.service.OurUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    
    private final OurUserDetailsService ourUserDetailsService;

    @Autowired
    public UserValidator(OurUserDetailsService ourUserDetailsService) {
        this.ourUserDetailsService = ourUserDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        try {
            ourUserDetailsService.loadUserByUsername(user.getEmail());
        }catch (UsernameNotFoundException usernameNotFoundException){
            return; // all is ok
        }

        errors.rejectValue("username", "", "User with this email already exists");
    }
}
