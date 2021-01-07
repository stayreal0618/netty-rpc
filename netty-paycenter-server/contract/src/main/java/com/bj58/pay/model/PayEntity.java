package com.bj58.pay.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yy
 * @version 1.0v
 * @description 支付实体
 * @date 2020/11/16 10:58
 */
@Data
public class PayEntity implements Serializable {

    private String outTradeNo;
    private Double payMoney;
    private String productDesc;
    private long userId;

    public PayEntity(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

}
