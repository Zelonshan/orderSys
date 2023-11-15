package com.ntlg.ordersys.controller;


import com.ntlg.ordersys.pojo.User;
import com.ntlg.ordersys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //查询会员信息
    @PostMapping(value = "/select")
    public ModelAndView selectLike(String search) {
        // 返回th:fragment代码片段
        ModelAndView view = new ModelAndView("users::userTable");
        view.addObject("user_list", userService.selectLike(search));
        return view;
    }

    //添加会员信息
    @PostMapping(value = "/insert")
    @ResponseBody
    public ModelAndView AddUsers(User user, @RequestParam("file") MultipartFile file) throws IOException {
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        int index=originalFilename.lastIndexOf('.')+1;//获取地址.的前面的数字，从0开始
        String type=originalFilename.substring(index);//从地址.开始截取后缀
        //保存图片的路径（这是存在我项目中的images下了，你们可以设置路径）
        String filePath="C:\\JAVA\\javaworkspace\\imgs\\headshot";
        //生成新文件名字
        String newFileName= UUID.randomUUID()+originalFilename;
        // 封装上传文件位置的全路径
        File targetFile  = new File(filePath,newFileName);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        user.setImg(newFileName);
        if(type.equals("jpg") || type.equals("gif") || type.equals("png")){

            userService.insertUsers(user);}
        ModelAndView view = new ModelAndView("users::userTable");
        view.addObject("user_list", userService.selectAllUsers());
        return view;
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteUsers(Integer id) {
        //先删除文件
        String imgPath = userService.findImgPath(id);
        String filePath="C:\\JAVA\\javaworkspace\\imgs\\headshot\\"+imgPath;
        File file = new File(filePath);
        if (file.delete()){
            // 通过编号删除用户
            userService.deleteUsers(id);}

        ModelAndView view = new ModelAndView("users::userTable");
        // 返回新的数据列表
        view.addObject("user_list", userService.selectAllUsers());
        return view;
    }

    @PostMapping(value = "/update")
    public ModelAndView updateUsers(User user,@RequestPart("file") MultipartFile file) throws IOException {
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        int index=originalFilename.lastIndexOf('.')+1;//获取地址.的前面的数字，从0开始
        String type=originalFilename.substring(index);//从地址.开始截取后缀
        //保存图片的路径（这是存在我项目中的images下了，你们可以设置路径）
        String filePath="C:\\JAVA\\javaworkspace\\imgs\\headshot";
        //生成新文件名字
        String newFileName= UUID.randomUUID()+originalFilename;
        // 封装上传文件位置的全路径
        File targetFile  = new File(filePath,newFileName);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        user.setImg(newFileName);
        if(type.equals("jpg") || type.equals("gif") || type.equals("png")){
            userService.updateUsers(user);}
        ModelAndView view = new ModelAndView("users::userTable");
        view.addObject("user_list", userService.selectAllUsers());
        return view;
    }

    //显示所有会员信息
    @RequestMapping(value = "/users")//访问路径
    public ModelAndView toIndex(){
        // 返回templates目录下users.html
        ModelAndView view = new ModelAndView("users");
        // 查询所有的用户，添加到model视图里
        view.addObject("user_list", userService.selectAllUsers());
        return view;

    }
}
