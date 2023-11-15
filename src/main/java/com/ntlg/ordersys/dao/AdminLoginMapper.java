package com.ntlg.ordersys.dao;

import com.ntlg.ordersys.pojo.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminLoginMapper {
    /**
     * 查询用户名是否存在，若存在，不允许注册
     * 注解@Param(value) 若value与可变参数相同，注解可省略
     * 注解@Results  列名和字段名相同，注解可省略
     * @param name
     * @return
     */
    @Select(value = "select name,password from admin where name=#{name}")
    @Results
            ({@Result(property = "name",column = "name"),
                    @Result(property = "password",column = "password")})
    Admin findUserByName(@Param("name") String name);



    /**
     * 注册  插入一条admin记录
     * @param admin
     * @return
     */
    @Insert("insert into admin values(#{id},#{name},#{password})")
    //加入该注解可以保存对象后，查看对象插入id
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void regist(Admin admin);

    /**
     * 登录
     *
     * @param admin
     * @return
     */
    @Select("select id from admin  where name = #{name} and password = #{password}")
    Integer login(Admin admin);


}
