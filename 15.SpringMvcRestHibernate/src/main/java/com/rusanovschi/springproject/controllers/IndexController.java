package com.rusanovschi.springproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

//    @GetMapping("/")
//    public String index(Model model){
//
//        return "static/index";
//    }

    @GetMapping("/login")
    public String login(){

        return "authentication/login";
    }
}
