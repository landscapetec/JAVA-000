package com.lsf.studymybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class OrderInfo {
    private long id;
    private String orderNo;
    private boolean childOrder;
    private String paraentOrderNo;
    private String memberInfoId;
    private Double money;
    //状态：110:待付款  115: 系统校验 120:卖家待发货 130：待签收 140：签收完成 \\n200：正常完结  \\n400：异常完结
    private String state;
    private Date addTime;
}
