package com.rusanovschi.spring.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model){

        return "views/static/index.html";
    }

    @GetMapping("/login")
    public String login(){

        return "views/authentication/login.html";
    }
}
