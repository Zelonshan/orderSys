package com.ntlg.ordersys.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@DynamicUpdate //动态更新
public class Types {

    //分类Id
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    //分类名字
    private String name;
}
