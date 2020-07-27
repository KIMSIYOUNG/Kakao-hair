package com.example.kakaohair;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JenkinsTest {

    @GetMapping("/hihi")
    public String jenkins() {
        return "jenkins works";
    }
}
