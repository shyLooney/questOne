package com.example.demo.web.controller;

import com.example.demo.repository.mongo.PersonMongoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
public class RegisterController {
    @GetMapping
    public String register() {
        return "register";
    }
}
