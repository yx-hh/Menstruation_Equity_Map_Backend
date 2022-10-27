package edu.uci.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/")
    public String hello() {
        return "<h1> Congratulations. You have successfully deployed the sample Spring Boot Application. </h1>";
    }
}
