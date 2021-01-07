package com.bj58.pay.service;

import com.bj58.pay.annotation.NettyServiceContract;
import com.bj58.pay.model.PayEntity;
import com.bj58.pay.rpc.constants.SerializeVersion;

/**
 * @author yy
 * @version 1.0v
 * @description 支付service
 * @date 2020/11/16 10:55
 */
@NettyServiceContract(lookup = "PayService", serverName = "paycenter", serializeVersion = SerializeVersion.NATIVE_JAVA)
public interface PayService {

    String pay(PayEntity payEntity) throws Exception;

}
