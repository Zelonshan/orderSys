package com.ntlg.ordersys.service.impl;

import com.ntlg.ordersys.dao.UserDao;
import com.ntlg.ordersys.pojo.Foods;
import com.ntlg.ordersys.pojo.User;
import com.ntlg.ordersys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public void insertUsers(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteUsers(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public void updateUsers(User user) {
        userDao.saveAndFlush(user);
    }

    @Override
    public List<User> selectAllUsers() {
        return userDao.findAll();
    }

    /**
     * 查询优先级：
     * 1.先查询是否为整型，为整型则通过ID主键查询，返回结果，不为整型则模糊查询其他字段
     * 2.模糊查询字段，忽略密码的模糊查询，对用户名和昵称进行模糊查询，返回结果
     * @param search 查询字段
     * @return 查询列表集合
     **/
    @Override
    public List<User> selectLike(String search) {
        List<User> list = new ArrayList<>();// 查询列表集合
        User user = new User();
        user.setName(search);
        try {
            Integer id = Integer.parseInt(search);
            Optional<User> optional = userDao.findById(id);
            if (!optional.isPresent()) {
                list = selectVague(user);
            } else {
                list.add(optional.get());
            }
        }catch (NumberFormatException e) {
            // 查询字段不为整型数据，捕获异常
            list = selectVague(user);
        }

        return list;
    }

    @Override
    public String findImgPath(Integer id) {
        User user = userDao.findById(id).get();
        return user.getImg();
    }

    //模糊查询
    private List<User> selectVague(User user) {
        List<User> list = null;
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreCase("id")
                .withIgnoreCase("phone");
        Example<User> example = Example.of(user, matcher);
        list = userDao.findAll(example);
        return list;
    }
}
