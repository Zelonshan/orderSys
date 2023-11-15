package com.ntlg.ordersys.dao;

import com.ntlg.ordersys.pojo.Orderlist;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderlistMapper {
    @Select(value = "select id,name,discount,quantity,orderinfo from orderlist where orderinfo =#{orderinfo}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "discount",column = "discount"),
            @Result(property = "quantity",column = "quantity"),
            @Result(property = "orderinfo",column = "orderinfo")
    })
    List<Orderlist> findByOrderinfo(@Param("orderinfo") String orderinfo);
}
