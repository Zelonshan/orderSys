package com.ntlg.ordersys.dao;

import com.ntlg.ordersys.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminDao extends JpaRepository<Admin,Integer> {


}
