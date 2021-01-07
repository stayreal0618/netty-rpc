package com.bj58.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.bj58.pay.dao.OrderDao;
import com.bj58.pay.db.OrderDb;
import com.bj58.pay.model.RemitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stayreal0618
 * @version 1.0v
 * @description 打款实现
 * @date 2020/11/16 14:57
 */
@Service("RemitService")
public class RemitServiceProvider implements RemitService {

    @Autowired
    private OrderDao orderDao;

    public String remit(RemitEntity remitEntity) throws Exception {

        OrderDb orderDb = new OrderDb();
        orderDb.setOrderMoney(remitEntity.getRemitMoney());
        orderDb.setOutTradeNo(remitEntity.getOutTradeNo());
        orderDb.setOrderType(2);
        orderDb.setProductName(remitEntity.getProductDesc());

        orderDao.createOrder(orderDb);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resultCode", "1111111");
        jsonObject.put("resultMsg", "SUCCESS");

        return jsonObject.toJSONString();

    }

}
