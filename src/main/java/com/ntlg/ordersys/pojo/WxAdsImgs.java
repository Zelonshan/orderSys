package com.ntlg.ordersys.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "wxadsimgs")
@Data
@DynamicUpdate //动态更新
public class WxAdsImgs {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String src;
    private Integer types;
}
