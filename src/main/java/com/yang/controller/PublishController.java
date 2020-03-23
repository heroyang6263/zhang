package com.yang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PunlishController {
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}
