package com.rusanovschi.shop.beautifulnails.service;

import com.rusanovschi.shop.beautifulnails.entity.User;
import com.rusanovschi.shop.beautifulnails.repository.UserRepository;
import com.rusanovschi.shop.beautifulnails.security.OurUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OurUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public OurUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findUserByEmail(username);

        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found!");

        return new OurUserDetails(user.get());
    }
}
