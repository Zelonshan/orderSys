package com.ntlg.ordersys.dao;

import com.ntlg.ordersys.pojo.Foodorders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;



public interface FoodordersDao extends JpaRepository<Foodorders,Integer> {


}
