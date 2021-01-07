package com.bj58.pay.rpc;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/12/3 下午11:27
 */
@Data
public class ResponseInfo implements Serializable {

    private static final long serialVersionUID = 927001768679354347L;
    private String sessionId;
    private Object response;

}
