package com.ntlg.ordersys.service.impl;

import com.ntlg.ordersys.dao.FoodTagsDao;
import com.ntlg.ordersys.pojo.FoodTags;

import com.ntlg.ordersys.service.FoodTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FoodTagsServiceImpl implements FoodTagsService {

    @Autowired
    private FoodTagsDao foodTagsDao;

    @Override
    public void insertFoodsTags(FoodTags foodTags) {
        foodTagsDao.save(foodTags);
    }

    @Override
    public void deleteFoodsTags(Integer id) {
        foodTagsDao.deleteById(id);
    }

    @Override
    public void updateFoodsTags(FoodTags foodTags) {
        foodTagsDao.saveAndFlush(foodTags);
    }

    @Override
    public List<FoodTags> selectAllFoodsTags() {
        return foodTagsDao.findAll();
    }
}
