package com.bj58.pay.service;

import com.bj58.pay.annotation.NettyServiceContract;
import com.bj58.pay.model.RemitEntity;
import com.bj58.pay.rpc.constants.SerializeVersion;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/11/16 10:56
 */
@NettyServiceContract(lookup = "RemitService", serverName = "paycenter", serializeVersion = SerializeVersion.NATIVE_JAVA)
public interface RemitService {

    String remit(RemitEntity remitEntity) throws Exception;

}
