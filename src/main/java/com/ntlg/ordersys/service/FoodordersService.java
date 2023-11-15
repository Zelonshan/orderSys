package com.ntlg.ordersys.service;

import com.ntlg.ordersys.pojo.Foodorders;

import java.util.List;

public interface FoodordersService {
    void insertFoodorders(Foodorders foodorders);
    void deleteFoodorder(Integer id);
    List<Foodorders> selectFoodorders();


    Foodorders findByOpenidAndOrderinfo(String userid, String orderinfo);

    List<Foodorders> findByUseridAndTaken(String openid,Integer taken);

    List<Foodorders> findByUserid(String userid);

    List<Foodorders> findByTaken(int taken);

    void updateTaken(String userid, String orderinfo,Integer taken);
}
