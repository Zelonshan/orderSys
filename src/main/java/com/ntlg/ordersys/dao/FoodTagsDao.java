package com.ntlg.ordersys.dao;

import com.ntlg.ordersys.pojo.FoodTags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTagsDao extends JpaRepository<FoodTags,Integer> {
}
