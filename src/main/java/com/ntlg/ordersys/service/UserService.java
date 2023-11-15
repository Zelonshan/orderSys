package com.ntlg.ordersys.service;


import com.ntlg.ordersys.pojo.User;

import java.util.List;

public interface UserService {
    void insertUsers(User user);
    void deleteUsers(Integer id);
    void updateUsers(User user);
    List<User> selectAllUsers();

    List<User> selectLike(String search);
    String findImgPath(Integer id);
}
