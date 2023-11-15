package com.ntlg.ordersys.dao;

import com.ntlg.ordersys.pojo.Orderlist;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface OrderlistDao extends JpaRepository<Orderlist,Integer> {

}
