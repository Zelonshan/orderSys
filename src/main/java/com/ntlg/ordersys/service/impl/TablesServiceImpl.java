package com.ntlg.ordersys.service.impl;

import com.ntlg.ordersys.dao.TablesDao;
import com.ntlg.ordersys.pojo.Tables;

import com.ntlg.ordersys.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TablesServiceImpl implements TablesService {
    @Autowired
    private TablesDao tablesDao;

    @Override
    public void insertTable(Tables tables) {
        tablesDao.save(tables);
    }

    @Override
    public void deleteTable(Integer id) {
        tablesDao.deleteById(id);
    }

    @Override
    public void updateTable(Tables tables) {
        tablesDao.saveAndFlush(tables);
    }

    @Override
    public List<Tables> selectAllTable() {
        return tablesDao.findAll();
    }

    @Override
    public void updateStateByName(Integer state, String name) {
        tablesDao.updateStateByName(state,name);
    }

}
