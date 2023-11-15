package com.ntlg.ordersys.service;

import com.ntlg.ordersys.pojo.Tables;


import java.util.List;

public interface TablesService {
    void insertTable(Tables tables);
    void deleteTable(Integer id);
    void updateTable(Tables tables);
    List<Tables> selectAllTable();

    void updateStateByName(Integer state,String name);
}
