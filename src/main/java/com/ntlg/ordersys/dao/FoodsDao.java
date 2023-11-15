package com.ntlg.ordersys.dao;

import com.ntlg.ordersys.pojo.Foods;


import org.springframework.data.jpa.repository.JpaRepository;




public interface FoodsDao extends JpaRepository<Foods,Integer> {



}
