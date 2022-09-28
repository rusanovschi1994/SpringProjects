package com.rusanovschi.shop.beautifulnails.controllers;

import com.rusanovschi.shop.beautifulnails.dto.AuthenticationDTO;
import com.rusanovschi.shop.beautifulnails.dto.UserDTO;
import com.rusanovschi.shop.beautifulnails.entity.User;
import com.rusanovschi.shop.beautifulnails.security.JWTUtil;
import com.rusanovschi.shop.beautifulnails.security.OurUserDetails;
import com.rusanovschi.shop.beautifulnails.service.RegistrationService;
import com.rusanovschi.shop.beautifulnails.util.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/jwtAuth")
public class AuthControllerJwt {

    private final RegistrationService registrationService;
    private final UserValidator userValidator;
    private final ModelMapper modelMapper;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public AuthControllerJwt(RegistrationService registrationService,
                             UserValidator userValidator,
                             ModelMapper modelMapper,
                             JWTUtil jwtUtil,
                             AuthenticationManager authenticationManager) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid UserDTO userDTO,
                                                   BindingResult bindingResult){

        User user = convertToUser(userDTO);

        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors())
            return Map.of("message", "error!");

        registrationService.save(user);

        String token = jwtUtil.generateToken(userDTO.getUsername());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO authenticationDTO){

        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                        authenticationDTO.getPassword());
        try {
            authenticationManager.authenticate(authInputToken);
        }catch (BadCredentialsException e){
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("jwt-token", token);
    }

    //method for  JWT authentification
    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OurUserDetails ourUserDetails = (OurUserDetails) authentication.getPrincipal();
        System.out.println(ourUserDetails.getUser());
        return ourUserDetails.getUsername();
    }

    public User convertToUser(UserDTO userDTO){
        return this.modelMapper.map(userDTO, User.class);
    }
}
