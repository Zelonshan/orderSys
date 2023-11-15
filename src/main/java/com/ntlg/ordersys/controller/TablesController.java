package com.ntlg.ordersys.controller;

import com.ntlg.ordersys.pojo.Tables;
import com.ntlg.ordersys.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/Table")
public class TablesController {
    //注入业务层对象
    @Autowired
    private TablesService tablesService;

    @RequestMapping("/table")
    public ModelAndView toIndex(){
        // 返回templates目录下tables.html
        ModelAndView view = new ModelAndView("tables");
        // 查询所有的用户，添加到model视图里
        view.addObject("tables_list", tablesService.selectAllTable());
        return view;
    }

    @PostMapping(value = "/insert")
    public ModelAndView insertTables(Tables tables) {
        // 插入数据
        tablesService.insertTable(tables);
        // 回传代码片段
        ModelAndView view = new ModelAndView("tables::tablesTable");
        view.addObject("tables_list", tablesService.selectAllTable());
        return view;
    }

    @PostMapping(value = "/update")
    public ModelAndView updateTables(Tables tables) {
        //插入数据
        tablesService.updateTable(tables);
        ModelAndView view = new ModelAndView("tables::tablesTable");
        view.addObject("tables_list", tablesService.selectAllTable());
        return view;
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteTables(Integer id) {
        // 通过编号删除分类
        tablesService.deleteTable(id);
        ModelAndView view = new ModelAndView("tables::tablesTable");
        // 返回新的数据列表
        view.addObject("tables_list", tablesService.selectAllTable());
        return view;
    }

    @PostMapping(value = "/caozuo")
    public ModelAndView CaozuoTables(String name){
        Integer state = 1;
        tablesService.updateStateByName(state,name);
        ModelAndView view = new ModelAndView("tables::tablesTable");
        // 返回新的数据列表
        view.addObject("tables_list", tablesService.selectAllTable());
        return view;
    }
}
