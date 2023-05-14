package com.mission.globalknowledge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/page/{id}")
    public String gotPost(@PathVariable(value = "id")Long id) {
        return "page";
    }
}
