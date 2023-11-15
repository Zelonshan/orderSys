package com.ntlg.ordersys.service;

import com.ntlg.ordersys.pojo.Orderlist;

import java.util.List;

public interface OrderlistService {
    void insertOrderlist(Orderlist orderlist);
    List<Orderlist> selectOrderlist(String orderinfo);


}
