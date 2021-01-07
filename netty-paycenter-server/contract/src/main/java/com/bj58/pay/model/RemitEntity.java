package com.bj58.pay.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/11/16 11:07
 */
@Data
public class RemitEntity implements Serializable {

    private double remitMoney;
    private String outTradeNo;
    private String productDesc;
    private long userId;

}
