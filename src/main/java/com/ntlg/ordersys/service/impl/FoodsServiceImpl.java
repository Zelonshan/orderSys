package com.ntlg.ordersys.service.impl;

import com.ntlg.ordersys.dao.FoodsDao;
import com.ntlg.ordersys.pojo.Admin;
import com.ntlg.ordersys.pojo.Foods;
import com.ntlg.ordersys.service.FoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class FoodsServiceImpl implements FoodsService {
    @Autowired
    private FoodsDao foodsDao;
    @Override
    public void insertFoods(Foods foods) {
        foodsDao.save(foods);
    }

    @Override
    public void deleteFoods(Integer id) {
        foodsDao.deleteById(id);
    }

    @Override
    public void updateFoods(Foods foods) {
        foodsDao.saveAndFlush(foods);
    }

    @Override
    public List<Foods> selectAllFoods() {
        return foodsDao.findAll();
    }

    /**
     * 查询优先级：
     * 1.先查询是否为整型，为整型则通过ID主键查询，返回结果，不为整型则模糊查询其他字段
     * 2.模糊查询字段，忽略密码的模糊查询，对用户名和昵称进行模糊查询，返回结果
     * @param search 查询字段
     * @return 查询列表集合
     **/
    @Override
    public List<Foods> selectLike(String search) {
        List<Foods> list = new ArrayList<>();// 查询列表集合
        Foods foods = new Foods();
        foods.setName(search);
        try {
            Integer id = Integer.parseInt(search);
            Optional<Foods> optional = foodsDao.findById(id);
            if (!optional.isPresent()) {
                list = selectVague(foods);
            } else {
                list.add(optional.get());
            }
        }catch (NumberFormatException e) {
            // 查询字段不为整型数据，捕获异常
            list = selectVague(foods);
        }

        return list;
    }

    @Override
    public String findImgPath(Integer id) {
     Foods foods= foodsDao.findById(id).get();
     return foods.getImg();
    }


    //模糊查询
    private List<Foods> selectVague(Foods foods) {
        List<Foods> list = null;
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreCase("id")
                .withIgnoreCase("describes");
        Example<Foods> example = Example.of(foods, matcher);
        list = foodsDao.findAll(example);
        return list;
    }

}
