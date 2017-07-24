/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.controller;

import com.model.User;
import com.model.VO.DataTablesPageResult;
import com.model.VO.Notification;
import com.model.VO.VUser;
import com.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by soong on 2017/7/22.
 */
@Controller
@RequestMapping(value = "/inspinia/users")
public class UserController {

    protected final String uri = "/inspinia/users";

    @Resource
    UserService userService;

    /**
     * 返回action视图页
     * @param view
     * @return
     */
    protected String viewPath(String view){
        return String.format("inspinia/user/%s", view);
    }

    @GetMapping(value = {"", "/"})
    public String list (Model model) throws Exception{
        return viewPath("list");
    }

    @RequestMapping(value = "datatables")
    @ResponseBody
    public ResponseEntity<DataTablesPageResult<VUser>> ajaxDataTables(@RequestParam(name = "start", defaultValue = "0") Integer start,
                                                                      @RequestParam(name = "length", defaultValue = "10") Integer length,
                                                                      @RequestParam(name = "search[value]") String keywords,
                                                                      @RequestParam(name = "draw", defaultValue = "1") Integer draw,
                                                                      @RequestParam(name = "order[0][column]", defaultValue = "0") Integer column,
                                                                      @RequestParam(name = "order[0][dir]", defaultValue = "asc") String order){
        Sort sort = new Sort(new Sort.Order(order.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , column.equals(0) ? "username" : "alias"),
                new Sort.Order(Sort.Direction.DESC, "createdDate"));

        Pageable pageable = new PageRequest(start / length, length, sort);

        Page<User> result;
        if (StringUtils.isEmpty(keywords)){
            result = userService.getUserRepository().findAll(pageable);
        }else{
            result = userService.getUserRepository().findByUsernameContains(keywords + "", pageable);
        }

        List<VUser> vusers = new ArrayList<>();
        int size = result.getContent().size();
        for (int i  =0; i < size; i++ ){
            User u = result.getContent().get(i);
            vusers.add(new VUser(u));
        }

        DataTablesPageResult<VUser> dataTables = new DataTablesPageResult<VUser>(draw, result.getTotalElements(), size, vusers);
        return new ResponseEntity<DataTablesPageResult<VUser>>(dataTables, HttpStatus.OK);
    }

    @GetMapping(value = "create")
    public String create(User user, Model model){
        model.addAttribute("user", user);

        return viewPath("create-update");
    }

    @PostMapping(value = "create")
    public String saveCreate(User user, Model model, RedirectAttributes attrs){
        boolean valid = validate(model, user, null);

        if (valid){
            userService.getUserRepository().save(user);
            Notification notify = Notification.success("新增用户", "操作成功");
            attrs.addFlashAttribute("notification", notify);
            return "redirect:" + uri;
        }else{
            return viewPath("create-update");
        }
    }

    @GetMapping(value = "{id}")
    public String read(@PathVariable(name = "id") String id, Model model){
        User user = userService.getUserRepository().findById(id);
        model.addAttribute("user", user);

        return viewPath("detail");
    }

    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(name = "id") User user, Model model){
        model.addAttribute(user);

        return viewPath("create-update");
    }

    @PostMapping(value = "update/{id}")
    public String saveUpdate(@PathVariable(name = "id") String id, User user, Model model){
        User pending = userService.getUserRepository().findById(id);
        boolean valid = validate(model, user, pending);
        if (valid){
            pending.setUsername(user.getUsername());
            pending.setAlias(user.getAlias());
            pending.setProvince(user.getProvince());
            pending.setCity(user.getCity());
            pending.setDistrict(user.getDistrict());
            pending.setAddress(user.getAddress());

            userService.getUserRepository().save(pending);

            return "redirect:/inspinia/users/" + id;
        }else{
            return viewPath("create-update");
        }
    }

    /**
     * 添加或修改时候的验证方法
     * @param model
     * @param newUser
     * @param oldUser
     * @return
     */
    protected boolean validate(Model model, User newUser, User oldUser){
        //基本验证
        List<String> errors = new ArrayList<>();

        if (StringUtils.isEmpty(newUser.getUsername())){
            errors.add("用户名不能为空");
        }else{
            String regex = "[a-zA-Z0-9]{4,12}";
            if (!Pattern.matches(regex, newUser.getUsername())){
                errors.add("用户名限字母或数字4-12位");
            }
        }

        if (StringUtils.isEmpty(newUser.getId())){
            if (StringUtils.isEmpty(newUser.getPassword())){
                errors.add("密码不能为空");
            }
        }

        //逻辑验证
        if (errors.size() == 0){
            //用户名存在性验证
            if (oldUser != null){
                if (!newUser.getUsername().equals(oldUser.getUsername())){
                    boolean exists = userService.getUserRepository().existsByUsername(newUser.getUsername());
                    if (exists){
                        errors.add("用户名已存在");
                    }
                }
            }else{
                boolean exists = userService.getUserRepository().existsByUsername(newUser.getUsername());
                if (exists){
                    errors.add("用户名已存在");
                }
            }
        }

        if (errors.size() != 0){
            Notification notification = Notification.error("", StringUtils.arrayToDelimitedString(errors.toArray(), "<br/>"));
            model.addAttribute("notification", notification);
            model.addAttribute("user", newUser);
            return false;
        }

        return true;
    }

}
