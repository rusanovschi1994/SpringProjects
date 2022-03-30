package com.rusanovschi.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class FirstController {

    @GetMapping("/hello")
    public String sayHello(){
        return "first/hello";
    }

    @GetMapping("/goodBuy")
    public String sayGoodBay(){
        return "first/goodBuy";
    }
}
