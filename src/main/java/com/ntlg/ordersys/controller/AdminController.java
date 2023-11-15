package com.ntlg.ordersys.controller;

import com.ntlg.ordersys.pojo.Admin;
import com.ntlg.ordersys.result.Result;
import com.ntlg.ordersys.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    //注入业务层对象
    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/select")
    public ModelAndView selectLike(String search) {
        // 返回th:fragment代码片段
        ModelAndView view = new ModelAndView("manage::adminTable");
        view.addObject("admin_list", adminService.selectLike(search));
        return view;
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteUser(Integer id) {
        // 通过编号删除用户
        adminService.deleteAdmin(id);
        ModelAndView view = new ModelAndView("manage::adminTable");
        // 返回新的数据列表
        view.addObject("admin_list", adminService.selectAllAdmin());
        return view;
    }

    @PostMapping(value = "/insert")
    public ModelAndView insertUser(Admin admin) {
        // 插入数据
        adminService.insertAdmin(admin);
        // 回传代码片段
        ModelAndView view = new ModelAndView("manage::userTable");
        view.addObject("admin_list", adminService.selectAllAdmin());
        return view;
    }

    @PostMapping(value = "/update")
    public ModelAndView updateUser(Admin admin) {
        adminService.updateAdmin(admin);
        ModelAndView view = new ModelAndView("manage::adminTable");
        view.addObject("admin_list", adminService.selectAllAdmin());
        return view;
    }

    @RequestMapping(value = "/manage")//访问路径
    public ModelAndView toIndex(){
        // 返回templates目录下manage.html
        ModelAndView view = new ModelAndView("manage");
        // 查询所有的用户，添加到model视图里
        view.addObject("admin_list", adminService.selectAllAdmin());
        return view;

    }



}
