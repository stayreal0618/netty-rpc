package com.bj58.pay.dao.impl;

import com.bj58.pay.dao.OrderDao;
import com.bj58.pay.db.OrderDb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/11/16 19:33
 */
@Repository
@Slf4j
public class OrderDaoImpl implements OrderDao {

    public OrderDb getOrderById(Long id) throws Exception {
        return null;
    }

    public void createOrder(OrderDb orderDb) throws Exception {
        log.info("create order orderDb = {}", orderDb);
    }
}
