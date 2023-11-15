package com.ntlg.ordersys.controller;


import com.ntlg.ordersys.pojo.FoodTags;
import com.ntlg.ordersys.pojo.Foods;
import com.ntlg.ordersys.pojo.Types;
import com.ntlg.ordersys.service.FoodTagsService;
import com.ntlg.ordersys.service.FoodsService;

import com.ntlg.ordersys.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/foods")
public class FoodsController {
    //注入业务层对象
    @Autowired
    private  FoodsService foodsService;

    //注入菜品分类
    @Autowired
    private TypesService typesService;

    //注入菜品标签
    @Autowired
    private FoodTagsService foodTagsService;

    @RequestMapping("/foods")
    public ModelAndView toIndex(){
        // 返回templates目录下foods.html
        ModelAndView view = new ModelAndView("foods");
        // 查询所有的用户，添加到model视图里
        view.addObject("food_list", foodsService.selectAllFoods());
        return view;

    }


    @PostMapping(value = "/select")
    public ModelAndView selectLike(String search) {
        // 返回th:fragment代码片段
        ModelAndView view = new ModelAndView("foods::foodTable");
        view.addObject("food_list", foodsService.selectLike(search));
        return view;
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteFoods(Integer id) {
        //先删除文件
        String imgPath = foodsService.findImgPath(id);
        String filePath="C:\\JAVA\\javaworkspace\\imgs\\"+imgPath;
        File file = new File(filePath);
        if (file.delete()){
        // 通过编号删除用户
        foodsService.deleteFoods(id);}

        ModelAndView view = new ModelAndView("foods::foodTable");
        // 返回新的数据列表
        view.addObject("food_list", foodsService.selectAllFoods());
        return view;
    }

    @PostMapping(value = "/insert")
    @ResponseBody
    public ModelAndView AddFoods(Foods foods,@RequestParam("file") MultipartFile file) throws IOException {
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        int index=originalFilename.lastIndexOf('.')+1;//获取地址.的前面的数字，从0开始
        String type=originalFilename.substring(index);//从地址.开始截取后缀
        //保存图片的路径（这是存在我项目中的images下了，你们可以设置路径）
        String filePath="C:\\JAVA\\javaworkspace\\imgs\\foodlist";
        //生成新文件名字
        String newFileName= UUID.randomUUID()+originalFilename;
        // 封装上传文件位置的全路径
        File targetFile  = new File(filePath,newFileName);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        foods.setImg(newFileName);
        if(type.equals("jpg") || type.equals("gif") || type.equals("png")){

                foodsService.insertFoods(foods);}
        ModelAndView view = new ModelAndView("foods::foodTable");
        view.addObject("food_list", foodsService.selectAllFoods());
        return view;
    }



    @PostMapping(value = "/update")
    public ModelAndView updatefoods(Foods foods,@RequestPart("file") MultipartFile file) throws IOException {
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        int index=originalFilename.lastIndexOf('.')+1;//获取地址.的前面的数字，从0开始
        String type=originalFilename.substring(index);//从地址.开始截取后缀
        //保存图片的路径（这是存在我项目中的images下了，你们可以设置路径）
        String filePath="C:\\JAVA\\javaworkspace\\imgs\\foodlist";
        //生成新文件名字
        String newFileName= UUID.randomUUID()+originalFilename;
        // 封装上传文件位置的全路径
        File targetFile  = new File(filePath,newFileName);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        foods.setImg(newFileName);
        if(type.equals("jpg") || type.equals("gif") || type.equals("png")){
        foodsService.updateFoods(foods);}
        ModelAndView view = new ModelAndView("foods::foodTable");
        view.addObject("food_list", foodsService.selectAllFoods());
        return view;
    }

    //菜品分类
    @RequestMapping("/foodsTypes")
    public ModelAndView toIndex2(){
        // 返回templates目录下types.html
        ModelAndView view = new ModelAndView("types");
        // 查询所有的用户，添加到model视图里
        view.addObject("types_list", typesService.selectAllTypes());
        return view;

    }

    @PostMapping(value = "/foodsTypes/insert")
    public ModelAndView insertFoodsTypes(Types types) {
        // 插入数据
        typesService.insertTypes(types);
        // 回传代码片段
        ModelAndView view = new ModelAndView("types::typesTable");
        view.addObject("types_list", typesService.selectAllTypes());
        return view;
    }

    @PostMapping(value = "/foodsTypes/update")
    public ModelAndView updateFoodsTypes(Types types) {
        typesService.updateTypes(types);
        ModelAndView view = new ModelAndView("types::typesTable");
        view.addObject("types_list", typesService.selectAllTypes());
        return view;
    }

    @PostMapping(value = "/foodsTypes/delete")
    public ModelAndView deleteFoodsTypes(Integer id) {
        // 通过编号删除分类
        typesService.deleteTypes(id);
        ModelAndView view = new ModelAndView("types::typesTable");
        // 返回新的数据列表
        view.addObject("types_list", typesService.selectAllTypes());
        return view;
    }

    //菜品标签
    @RequestMapping("/foodTags")
    public ModelAndView toIndex3(){
        // 返回templates目录下foodTags.html
        ModelAndView view = new ModelAndView("foodTags");
        // 查询所有的用户，添加到model视图里
        view.addObject("foodTags_list", foodTagsService.selectAllFoodsTags());
        return view;

    }

    @PostMapping(value = "/foodsTags/insert")
    public ModelAndView insertFoodTags(FoodTags foodTags) {
        // 插入数据
        foodTagsService.insertFoodsTags(foodTags);
        // 回传代码片段
        ModelAndView view = new ModelAndView("foodTags::foodTagsTable");
        view.addObject("foodTags_list", foodTagsService.selectAllFoodsTags());
        return view;
    }

    @PostMapping(value = "/foodsTags/update")
    public ModelAndView updateFoodTags(FoodTags foodTags) {

        foodTagsService.updateFoodsTags(foodTags);

        ModelAndView view = new ModelAndView("foodTags::foodTagsTable");
        view.addObject("foodTags_list", foodTagsService.selectAllFoodsTags());
        return view;
    }

    @PostMapping(value = "/foodsTags/delete")
    public ModelAndView deleteFoodTags(Integer id) {
        // 通过编号删除分类
        foodTagsService.deleteFoodsTags(id);
        ModelAndView view = new ModelAndView("foodTags::foodTagsTable");
        // 返回新的数据列表
        view.addObject("foodTags_list", foodTagsService.selectAllFoodsTags());
        return view;
    }
}
