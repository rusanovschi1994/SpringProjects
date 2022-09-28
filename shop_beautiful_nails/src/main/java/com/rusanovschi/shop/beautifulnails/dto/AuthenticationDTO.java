package com.rusanovschi.shop.beautifulnails.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class AuthenticationDTO {

    @Email(message = "Email is not valid")
    private String username;

    @Size(min = 3, message = "Password should have 3 minim characters")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
