package com.bj58.pay.dao;

import com.bj58.pay.db.OrderDb;
import org.springframework.stereotype.Repository;

/**
 * @author stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/11/16 19:33
 */

public interface OrderDao {

    OrderDb getOrderById(Long id) throws Exception;

    void createOrder(OrderDb orderDb) throws Exception;

}
