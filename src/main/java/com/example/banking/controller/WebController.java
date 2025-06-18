package com.example.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    @Autowired
    private BankController bankController;

    @GetMapping("/")
    public String hello(Model model){
        //.addAttribute("message", "Hello Gaurav!");
        return "bankHome";
    }

    @GetMapping("/getAccountsDetailsWeb")
    public String getAccountDetailsWeb(Model model){
        model.addAttribute("customers", bankController.getAllCustomerInfo());
        return "getAccountsDetailsWeb";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

//    @PostMapping("/validateLogin")
//    public String validateLogin(Model model){
//
//    }

}
