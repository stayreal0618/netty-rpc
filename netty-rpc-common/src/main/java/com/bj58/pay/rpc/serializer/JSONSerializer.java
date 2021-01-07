package com.bj58.pay.rpc.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bj58.pay.rpc.constants.SerializeVersion;

/**
 * @author stayreal0618
 * @version 1.0v
 * @description 因为没有很好的方法进行反序列化requestInfo中的param
 * @date 2020/11/28 下午5:54
 */
@Deprecated
public class JSONSerializer implements Serializer{

    public byte getSerializerAlgorithm() {
        return SerializeVersion.JSON;
    }

    public byte[] serialize(Object object) {
        return JSONObject.toJSONString(object).getBytes();
    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSONObject.parseObject(new String(bytes), clazz);

    }

}
