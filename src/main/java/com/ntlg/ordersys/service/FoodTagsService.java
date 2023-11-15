package com.ntlg.ordersys.service;

import com.ntlg.ordersys.pojo.FoodTags;


import java.util.List;

public interface FoodTagsService {
    void insertFoodsTags(FoodTags foodTags);
    void deleteFoodsTags(Integer id);
    void updateFoodsTags(FoodTags foodTags);
    List<FoodTags> selectAllFoodsTags();
}
