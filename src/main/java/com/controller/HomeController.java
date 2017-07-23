package com.controller;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by soong on 2017/7/22.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public String index(Model model){
        return "index";
    }

}
