package com.ntlg.ordersys.dao;

import com.ntlg.ordersys.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,Integer> {

}
