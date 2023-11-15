package com.ntlg.ordersys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orderss {
    private String name;
    private Integer quantity;
    private Double discount;
    private Integer typesid;
    private String describes;
    private String description;
    private String img;
    private Integer id;
    private Double price;

}
