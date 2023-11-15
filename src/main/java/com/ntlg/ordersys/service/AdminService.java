package com.ntlg.ordersys.service;

import com.ntlg.ordersys.pojo.Admin;
import com.ntlg.ordersys.result.Result;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {
    void insertAdmin(Admin admin);// 添加用户

    void deleteAdmin(Integer id);// 删除用户

    void updateAdmin(Admin admin);// 修改用户

    List<Admin> selectAllAdmin();// 查询所有用户

    List<Admin> selectLike(String search);// 模糊查询


    String login(Admin admin);

    Boolean regist(Admin admin);


}
