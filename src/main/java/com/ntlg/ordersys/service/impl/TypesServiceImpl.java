package com.ntlg.ordersys.service.impl;

import com.ntlg.ordersys.dao.TypesDao;
import com.ntlg.ordersys.pojo.Types;
import com.ntlg.ordersys.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypesServiceImpl implements TypesService {

    @Autowired
    private TypesDao typesDao;

    @Override
    public void insertTypes(Types types) {
        typesDao.save(types);
    }

    @Override
    public void deleteTypes(Integer id) {
        typesDao.deleteById(id);
    }

    @Override
    public void updateTypes(Types types) {
        typesDao.saveAndFlush(types);
    }

    @Override
    public List<Types> selectAllTypes() {
        return typesDao.findAll();
    }
}
