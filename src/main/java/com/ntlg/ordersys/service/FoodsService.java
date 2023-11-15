package com.ntlg.ordersys.service;

import com.ntlg.ordersys.pojo.Foods;

import java.util.List;

public interface FoodsService {
    void insertFoods(Foods foods);
    void deleteFoods(Integer id);
    void updateFoods(Foods foods);
    List<Foods> selectAllFoods();

    List<Foods> selectLike(String search);
    String findImgPath(Integer id);

}
