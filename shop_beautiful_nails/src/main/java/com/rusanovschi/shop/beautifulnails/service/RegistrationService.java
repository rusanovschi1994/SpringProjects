package com.rusanovschi.shop.beautifulnails.service;

import com.rusanovschi.shop.beautifulnails.entity.User;
import com.rusanovschi.shop.beautifulnails.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(User user){

        user.setEnabled(true);
        user.setCreatedAt(new Date());
        userRepository.save(user);
    }
}
