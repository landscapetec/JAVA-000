package com.lsf.studymybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    private long id;
    private long orderInfoId;
    private String productId;
    private String productName;
    private double productPrice;
    private int productCount;
    private double productDiscountPrice;
    private String state;
}
