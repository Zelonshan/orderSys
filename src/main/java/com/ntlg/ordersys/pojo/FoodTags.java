package com.ntlg.ordersys.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Data
@Table(name = "foodtags")
@DynamicUpdate //动态更新
public class FoodTags {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String types;

    private Integer codes;

}
