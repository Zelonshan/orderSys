package com.ntlg.ordersys.service.impl;

import com.ntlg.ordersys.dao.AdminDao;
import com.ntlg.ordersys.dao.AdminLoginMapper;
import com.ntlg.ordersys.pojo.Admin;
import com.ntlg.ordersys.result.Result;
import com.ntlg.ordersys.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao admindao;
    @Autowired
    private AdminLoginMapper adminLoginMapper;
    @Override
    public void insertAdmin(Admin admin){
        admindao.save(admin);
    }

    @Override
    public void deleteAdmin(Integer id){admindao.deleteById(id);}

    @Override
    public void updateAdmin(Admin admin) {
        admindao.saveAndFlush(admin);
    }

    @Override
    public List<Admin> selectAllAdmin() {
        return admindao.findAll();
    }

    /**
     * 查询优先级：
     * 1.先查询是否为整型，为整型则通过ID主键查询，返回结果，不为整型则模糊查询其他字段
     * 2.模糊查询字段，忽略密码的模糊查询，对用户名和昵称进行模糊查询，返回结果
     * @param search 查询字段
     * @return 查询列表集合
    **/
    @Override
    public List<Admin> selectLike(String search) {
        List<Admin> list = new ArrayList<>();// 查询列表集合
        Admin admin = new Admin();
        admin.setName(search);
        try {
            Integer id = Integer.parseInt(search);
            Optional<Admin> optional = admindao.findById(id);
            if (!optional.isPresent()) {
                list = selectVague(admin);
            } else {
                list.add(optional.get());
            }
        }catch (NumberFormatException e) {
            // 查询字段不为整型数据，捕获异常
            list = selectVague(admin);
        }

        return list;
    }
    //登录
    @Override
    public String login(Admin admin) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Integer userId= adminLoginMapper.login(admin);
            if(userId == null){
                result.setMsg("用户名或密码错误");
            }else{
                result.setMsg("登录成功");
                result.setSuccess(true);
                admin.setId(userId);
                result.setDetail(admin);
                return "index";
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return "login";

    }


    //模糊查询
    private List<Admin> selectVague(Admin admin) {
        List<Admin> list = null;
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreCase("id")
                .withIgnoreCase("password");
        Example<Admin> example = Example.of(admin, matcher);
        list = admindao.findAll(example);
        return list;
    }

    //注册
    @Override
    public Boolean regist(Admin admin) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Admin existAdmin = adminLoginMapper.findUserByName(admin.getName());
            if(existAdmin != null){
                //如果用户名已存在
                result.setMsg("用户名已存在");
            }else{
                adminLoginMapper.regist(admin);
                //System.out.println(user.getId());
                result.setMsg("注册成功");
                result.setSuccess(true);
                result.setDetail(admin);
                return true;
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


}
