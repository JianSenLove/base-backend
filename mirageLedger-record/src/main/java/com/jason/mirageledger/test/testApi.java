package com.jason.mirageledger.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class testApi {

    @GetMapping
    public String createtest() {
        return "哎哟不错噢";
    }
}
