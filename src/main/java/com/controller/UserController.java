/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.controller;

import com.model.XUser;
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
        return String.format("inspinia/XUser/%s", view);
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

        Page<XUser> result;
        if (StringUtils.isEmpty(keywords)){
            result = userService.getUserRepository().findAll(pageable);
        }else{
            result = userService.getUserRepository().findByUsernameContains(keywords + "", pageable);
        }

        List<VUser> vusers = new ArrayList<>();
        int size = result.getContent().size();
        for (int i  =0; i < size; i++ ){
            XUser u = result.getContent().get(i);
            vusers.add(new VUser(u));
        }

        DataTablesPageResult<VUser> dataTables = new DataTablesPageResult<VUser>(draw, result.getTotalElements(), size, vusers);
        return new ResponseEntity<DataTablesPageResult<VUser>>(dataTables, HttpStatus.OK);
    }

    @GetMapping(value = "create")
    public String create(XUser XUser, Model model){
        model.addAttribute("XUser", XUser);

        return viewPath("create-update");
    }

    @PostMapping(value = "create")
    public String saveCreate(XUser XUser, Model model, RedirectAttributes attrs){
        boolean valid = validate(model, XUser, null);

        if (valid){
            userService.getUserRepository().save(XUser);
            Notification notify = Notification.success("新增用户", "操作成功");
            attrs.addFlashAttribute("notification", notify);
            return "redirect:" + uri;
        }else{
            return viewPath("create-update");
        }
    }

    @GetMapping(value = "{id}")
    public String read(@PathVariable(name = "id") String id, Model model){
        XUser XUser = userService.getUserRepository().findById(id);
        model.addAttribute("XUser", XUser);

        return viewPath("detail");
    }

    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(name = "id") XUser XUser, Model model){
        model.addAttribute(XUser);

        return viewPath("create-update");
    }

    @PostMapping(value = "update/{id}")
    public String saveUpdate(@PathVariable(name = "id") String id, XUser XUser, Model model){
        XUser pending = userService.getUserRepository().findById(id);
        boolean valid = validate(model, XUser, pending);
        if (valid){
            pending.setUsername(XUser.getUsername());
            pending.setAlias(XUser.getAlias());
            pending.setProvince(XUser.getProvince());
            pending.setCity(XUser.getCity());
            pending.setDistrict(XUser.getDistrict());
            pending.setAddress(XUser.getAddress());

            userService.getUserRepository().save(pending);

            return "redirect:/inspinia/users/" + id;
        }else{
            return viewPath("create-update");
        }
    }

    /**
     * 添加或修改时候的验证方法
     * @param model
     * @param newXUser
     * @param oldXUser
     * @return
     */
    protected boolean validate(Model model, XUser newXUser, XUser oldXUser){
        //基本验证
        List<String> errors = new ArrayList<>();

        if (StringUtils.isEmpty(newXUser.getUsername())){
            errors.add("用户名不能为空");
        }else{
            String regex = "[a-zA-Z0-9]{4,12}";
            if (!Pattern.matches(regex, newXUser.getUsername())){
                errors.add("用户名限字母或数字4-12位");
            }
        }

        if (StringUtils.isEmpty(newXUser.getId())){
            if (StringUtils.isEmpty(newXUser.getPassword())){
                errors.add("密码不能为空");
            }
        }

        //逻辑验证
        if (errors.size() == 0){
            //用户名存在性验证
            if (oldXUser != null){
                if (!newXUser.getUsername().equals(oldXUser.getUsername())){
                    boolean exists = userService.getUserRepository().existsByUsername(newXUser.getUsername());
                    if (exists){
                        errors.add("用户名已存在");
                    }
                }
            }else{
                boolean exists = userService.getUserRepository().existsByUsername(newXUser.getUsername());
                if (exists){
                    errors.add("用户名已存在");
                }
            }
        }

        if (errors.size() != 0){
            Notification notification = Notification.error("", StringUtils.arrayToDelimitedString(errors.toArray(), "<br/>"));
            model.addAttribute("notification", notification);
            model.addAttribute("XUser", newXUser);
            return false;
        }

        return true;
    }

}
