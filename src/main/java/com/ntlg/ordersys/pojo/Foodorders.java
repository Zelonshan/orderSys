package com.ntlg.ordersys.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicUpdate //动态更新
@DynamicInsert //动态加
@EntityListeners(AuditingEntityListener.class)
public class Foodorders {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String orderinfo;
    @CreatedDate
    private Date ordertime;
    private String ordernum;
    private String meunnumber;
    private String userid;
    private Integer taken;
    private Double totalprice;
    private Integer totalnum;

}
