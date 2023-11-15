package com.ntlg.ordersys.dao;

import com.ntlg.ordersys.pojo.Admin;
import com.ntlg.ordersys.pojo.WxAdsImgs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WxAdsImgsDao extends JpaRepository<WxAdsImgs,Integer> {
    List<WxAdsImgs> findAllByTypes(Integer types);
}
