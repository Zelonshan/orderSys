package com.ntlg.ordersys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrder implements Serializable {
    private Integer totalNum;
    private Double totalPrice;
    private String name;
    private List<Orderss> cartList;
    private String openid;

}
