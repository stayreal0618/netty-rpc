package com.bj58.pay.rpc;

import lombok.Data;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/11/14 17:11
 */
@Data
public class RpcData {

    public static final int MAGICAL_NUMBER = 1990618;
    private byte protocolVersion;
    private byte serializeVersion;

    private RequestInfo requestInfo;
    private ResponseInfo responseInfo;

}
