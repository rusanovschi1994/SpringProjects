package com.rusanovschi.springproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model){

        System.out.println("starting index() method...");
        return "static/index";
    }
}