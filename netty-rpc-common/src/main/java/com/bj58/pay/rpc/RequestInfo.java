package com.bj58.pay.rpc;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author yy
 * @version 1.0v
 * @description rpc请求时候的请求实体
 * @date 2020/11/28 下午5:00
 */
@Data
@ToString
public class RequestInfo implements Serializable {

    private static final long serialVersionUID = 6161653201864374556L;
    private String sessionId;
    private String className;
    private String methodName;
    private String serverName;
    private String beanName;

    private List<Entry<String, Object>> param;

}
