package com.bj58.pay.db;

import lombok.Data;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/11/16 19:35
 */
@Data
public class OrderDb {

    private Long id;
    private String outTradeNo;
    private Double orderMoney;
    private String productName;
    private Integer orderType;


}
