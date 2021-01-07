package com.bj58.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.bj58.pay.dao.OrderDao;
import com.bj58.pay.db.OrderDb;
import com.bj58.pay.model.PayEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stayreal0618
 * @version 1.0v
 * @description 支付实现
 * @date 2020/11/16 14:56
 */
@Service("PayService")
@Slf4j
public class PayServiceProvider implements PayService {

    @Autowired
    private OrderDao orderDao;


    public String pay(PayEntity payEntity) throws Exception {

        OrderDb orderDb = new OrderDb();
        orderDb.setOrderMoney(payEntity.getPayMoney());
        orderDb.setOutTradeNo(payEntity.getOutTradeNo());
        orderDb.setOrderType(1);
        orderDb.setProductName(payEntity.getProductDesc());

        orderDao.createOrder(orderDb);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resultCode", "1111111");
        jsonObject.put("resultMsg", "SUCCESS");

        return jsonObject.toJSONString();

    }
}
