package com.ntlg.ordersys.controller;


import com.ntlg.ordersys.pojo.Foods;
import com.ntlg.ordersys.pojo.WxAdsImgs;
import com.ntlg.ordersys.service.WxAdsImgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/wxAdsImgs")
public class WxAdsImgsController {
    @Autowired
    private WxAdsImgsService wxAdsImgsService;

    @RequestMapping("/wxAdsImgs")
    public ModelAndView toIndex(){
        // 返回templates目录下adsImgs.html
        ModelAndView view = new ModelAndView("adsImgs");
        // 查询所有的用户，添加到model视图里
        view.addObject("adsImgs_list", wxAdsImgsService.selectAllImgs());
        return view;
    }

    @PostMapping(value = "/insert")
    @ResponseBody
    public ModelAndView AddAdsImgs(WxAdsImgs wxAdsImgs, @RequestParam("file") MultipartFile file) throws IOException {
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        int index=originalFilename.lastIndexOf('.')+1;//获取地址.的前面的数字，从0开始
        String type=originalFilename.substring(index);//从地址.开始截取后缀
        //保存图片的路径（这是存在我项目中的images下了，你们可以设置路径）
        String filePath="C:\\JAVA\\javaworkspace\\imgs\\wxadsimage";
        //生成新文件名字
        String newFileName= UUID.randomUUID()+originalFilename;
        // 封装上传文件位置的全路径
        File targetFile  = new File(filePath,newFileName);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        wxAdsImgs.setSrc(newFileName);
        if(type.equals("jpg") || type.equals("gif") || type.equals("png")){

            wxAdsImgsService.insertImgs(wxAdsImgs);}
        ModelAndView view = new ModelAndView("adsImgs::imgsTable");
        view.addObject("adsImgs_list", wxAdsImgsService.selectAllImgs());
        return view;
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteAdsImgs(Integer id) {
        //先删除文件
        String imgPath = wxAdsImgsService.findImgPath(id);
        String filePath="C:\\JAVA\\javaworkspace\\imgs\\wxadsimage\\"+imgPath;
        File file = new File(filePath);
        if (file.delete()){
            // 通过编号删除用户
            wxAdsImgsService.deleteImgs(id);}

        ModelAndView view = new ModelAndView("adsImgs::imgsTable");
        // 返回新的数据列表
        view.addObject("adsImgs_list", wxAdsImgsService.selectAllImgs());
        return view;
    }

    @PostMapping(value = "/update")
    public ModelAndView updatefoods(WxAdsImgs wxAdsImgs, @RequestPart("file") MultipartFile file) throws IOException {
        //先删除文件
        Integer id = wxAdsImgs.getId();
        String imgPath = wxAdsImgsService.findImgPath(id);
        String filePath="C:\\JAVA\\javaworkspace\\imgs\\wxadsimage\\"+imgPath;
        File file1 = new File(filePath);
        if (file1.delete()){
            // 通过编号删除用户
            wxAdsImgsService.deleteImgs(id);}

        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        int index=originalFilename.lastIndexOf('.')+1;//获取地址.的前面的数字，从0开始
        String type=originalFilename.substring(index);//从地址.开始截取后缀
        //保存图片的路径（这是存在我项目中的images下了，你们可以设置路径）
        String filePath1="C:\\JAVA\\javaworkspace\\imgs\\wxadsimage";
        //生成新文件名字
        String newFileName= UUID.randomUUID()+originalFilename;
        // 封装上传文件位置的全路径
        File targetFile  = new File(filePath1,newFileName);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        wxAdsImgs.setSrc(newFileName);
        if(type.equals("jpg") || type.equals("gif") || type.equals("png")){
            wxAdsImgsService.updateImgs(wxAdsImgs);}
        ModelAndView view = new ModelAndView("adsImgs::imgsTable");
        view.addObject("adsImgs_list", wxAdsImgsService.selectAllImgs());
        return view;
    }
}
