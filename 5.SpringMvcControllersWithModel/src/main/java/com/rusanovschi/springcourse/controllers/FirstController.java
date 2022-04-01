package com.rusanovschi.springcourse.controllers;

import org.springframework.expression.EvaluationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class FirstController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "surname", required = false) String surname,
                           Model model){

        //String name = request.getParameter("name");
        //String surname = request.getParameter("surname");
        //System.out.println("Hello " + name + " " + surname );

        model.addAttribute("message", "Hello " + name + " " + surname);

        return "first/hello";
    }

    @GetMapping("/goodBuy")
    public String sayGoodBay(){

        return "first/goodBuy";
    }

    @GetMapping("/calculator")
    public String Calculator(@RequestParam(value = "a") int a,
                             @RequestParam(value = "b") int b,
                             @RequestParam(value = "action") String action,
                             Model model){

        double result;

        switch(action){
            case "multiplication":
                result = a * b;
                break;
            case "division":
                result =a / (double) b;
                break;
            case "addition":
                result = a + b;
                break;
            case "subtraction":
                result = a - b;
                break;
            default:
                result = 0;
        }

        model.addAttribute("result", result);

        return "calculator/calculatorView";
    }
}
