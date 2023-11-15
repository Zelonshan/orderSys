package com.ntlg.ordersys.service.impl;

import com.ntlg.ordersys.dao.OrderlistDao;
import com.ntlg.ordersys.dao.OrderlistMapper;
import com.ntlg.ordersys.pojo.Orderlist;
import com.ntlg.ordersys.service.OrderlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderlistServiceImpl implements OrderlistService {
    @Autowired
    private OrderlistDao orderlistDao;

    @Autowired
    private OrderlistMapper orderlistMapper;
    @Override
    public void insertOrderlist(Orderlist orderlist) {
        orderlistDao.save(orderlist);
    }

    @Override
    public List<Orderlist> selectOrderlist(String orderinfo) {
        return orderlistMapper.findByOrderinfo(orderinfo);
    }
}
