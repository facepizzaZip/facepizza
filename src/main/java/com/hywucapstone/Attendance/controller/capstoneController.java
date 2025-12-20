package com.hywucapstone.Attendance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class capstoneController {

    @GetMapping("/hello") 
    public String sayHello() {
        return "Hello, World! I'm back!";
    }
}
