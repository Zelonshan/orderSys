package com.ntlg.ordersys.dao;

import com.ntlg.ordersys.pojo.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface TablesDao extends JpaRepository<Tables,Integer> {
    @Transactional
    @Modifying
    @Query("update Tables  t set t.state = ?1 where t.name = ?2")
    void updateStateByName(Integer state,String name);
}
