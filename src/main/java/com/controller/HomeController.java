package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by soong on 2017/7/22.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("")
    public String index(Model model){

        throw new RuntimeException("fewfew");
    }

}
