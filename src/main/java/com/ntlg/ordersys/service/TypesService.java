package com.ntlg.ordersys.service;


import com.ntlg.ordersys.pojo.Types;


import java.util.List;

public interface TypesService {
    void insertTypes(Types types);
    void deleteTypes(Integer id);
    void updateTypes(Types types);
    List<Types> selectAllTypes();
}
