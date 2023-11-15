package com.ntlg.ordersys.service.impl;

import com.ntlg.ordersys.dao.FoodordersDao;
import com.ntlg.ordersys.dao.FoodordersMapper;
import com.ntlg.ordersys.pojo.Foodorders;
import com.ntlg.ordersys.service.FoodordersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FoodordersServiceImpl implements FoodordersService {

    @Autowired
    private FoodordersDao foodordersDao;

    @Autowired
    private FoodordersMapper foodordersMapper;

    @Override
    public void insertFoodorders(Foodorders foodorders) {
        foodordersDao.save(foodorders);
    }

    @Override
    public void deleteFoodorder(Integer id) {
        foodordersDao.deleteById(id);
    }

    @Override
    public List<Foodorders> selectFoodorders() {
        return foodordersDao.findAll();
    }

    @Override
    public Foodorders findByOpenidAndOrderinfo(String userid, String orderinfo) {
        return foodordersMapper.findFoodordersByUseridAndOrderinfo(userid, orderinfo);
    }

    @Override
    public List<Foodorders> findByUseridAndTaken(String openid , Integer taken) {
        return foodordersMapper.findFoodordersByUseridAndTaken(openid, taken);
    }

    @Override
    public List<Foodorders> findByUserid(String userid) {
        return foodordersMapper.findFoodordersByUserid(userid);
    }

    @Override
    public List<Foodorders> findByTaken(int taken) {
        return foodordersMapper.findByTaken(taken);
    }

    @Override
    public void updateTaken(String userid, String orderinfo, Integer taken) {
        foodordersMapper.updateTakenByUseridAndOrderinfo(userid,orderinfo,taken);
    }


}
