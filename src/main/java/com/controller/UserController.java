/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.controller;

import com.model.User;
import com.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by soong on 2017/7/22.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Resource
    UserService userService;

    protected String viewPath(String view){
        return String.format("inspinia/user/%s", view);
    }

    @GetMapping(value = {"", "/"})
    public String all (Model model) throws Exception{
        List<User> users = userService.getUserRepository().findAll();
        model.addAttribute("users", users);

        throw new Exception("FEWFEW");
//        return viewPath("list");
    }


//    @GetMapping(value = {"", "/"})
//    public HttpEntity<List<User>> all(){
//        List<User> inspinia = userService.getUserRepository().findAll();
//
//        return new HttpEntity<>(inspinia);
//    }
//
//    @GetMapping(value = "create")
//    public HttpEntity<User> create(User user){
//        user = userService.getUserRepository().save(user);
//        return new HttpEntity<User>(user);
//    }
//
//    @GetMapping(value = "{id}")
//    public HttpEntity<User> get(@PathVariable(name = "id") User user){
//        return new HttpEntity<User>(user);
//    }

}
