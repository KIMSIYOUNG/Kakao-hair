package com.example.kakaohair;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JenkinsCICDController {

    @GetMapping("/jenkins")
    public String jenkinsHello () {
        return "hello jenkins and docker";
    }
}
