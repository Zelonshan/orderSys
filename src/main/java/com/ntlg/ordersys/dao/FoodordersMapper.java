package com.ntlg.ordersys.dao;


import com.ntlg.ordersys.pojo.Foodorders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FoodordersMapper {
    @Select(value = "select orderinfo,ordertime,ordernum,meunnumber,userid from foodorders where userid=#{userid} and orderinfo=#{orderinfo}")
    @Results
            ({@Result(property = "orderinfo",column = "orderinfo"),
                    @Result(property = "ordertime",column = "ordertime"),
            @Result(property = "ordernum",column = "ordernum"),
            @Result(property = "meunnumber",column = "meunnumber"),
            @Result(property = "userid",column = "userid")})
    Foodorders findFoodordersByUseridAndOrderinfo(@Param("userid") String userid,@Param("orderinfo") String orderinfo);

    @Select(value = "select id,ordertime,totalprice,taken from foodorders where userid=#{userid} and taken = #{taken} order by id desc")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "ordertime",column = "ordertime"),
            @Result(property = "totalprice",column = "totalprice"),
            @Result(property = "taken",column = "taken")
    })
    List<Foodorders> findFoodordersByUseridAndTaken(@Param("userid") String userid,@Param("taken") Integer taken);

    @Select(value = "select id,orderinfo,ordertime,meunnumber,taken,ordernum,userid,totalprice,totalnum from foodorders where userid=#{userid} order by ordertime desc")
    @Results
            ({@Result(property = "id",column = "id"),
                    @Result(property = "orderinfo",column = "orderinfo"),
                    @Result(property = "ordertime",column = "ordertime"),
                    @Result(property = "meunnumber",column = "meunnumber"),
                    @Result(property = "taken",column = "taken"),
                    @Result(property = "ordernum",column = "ordernum"),
                    @Result(property = "userid",column = "userid"),
                    @Result(property = "totalprice",column = "totalprice"),
                    @Result(property = "totalnum",column = "totalnum")
            })
    List<Foodorders> findFoodordersByUserid(@Param("userid") String userid);

    @Select(value = "select id,orderinfo,ordertime,meunnumber,taken,ordernum,userid,totalprice,totalnum from foodorders where taken=#{taken} order by ordertime desc")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "orderinfo",column = "orderinfo"),
            @Result(property = "ordertime",column = "ordertime"),
            @Result(property = "meunnumber",column = "meunnumber"),
            @Result(property = "taken",column = "taken"),
            @Result(property = "ordernum",column = "ordernum"),
            @Result(property = "userid",column = "userid"),
            @Result(property = "totalprice",column = "totalprice"),
            @Result(property = "totalnum",column = "totalnum")
    })
    List<Foodorders> findByTaken(@Param("taken") int taken);
    @Update(value = "update foodorders set taken = #{taken} where userid = #{userid} and oderinfo = #{orderinfo}")
    void updateTakenByUseridAndOrderinfo(@Param("userid") String userid, String orderinfo,@Param("taken") Integer taken);
}
