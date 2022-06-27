package com.rusanovschi.spring.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model){

        return "static/index";
   }

//    @GetMapping("/login")
//    public String login(){
//
//        return "views/authentication/login.html";
//    }
}
