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
public class Foods {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String describes;
    private Double price;
    private String img;
    private String description;
    private Double discount;
    private Integer quantity;
    private Integer typesid;

}
