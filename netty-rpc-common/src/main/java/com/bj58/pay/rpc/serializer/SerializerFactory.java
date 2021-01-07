package com.bj58.pay.rpc.serializer;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/12/7 下午7:32
 */
public class SerializerFactory {

    public static Serializer getSerializerByVersion(byte version) {

        if (Serializer.JSON_TYPE.getSerializerAlgorithm() == version) {
            return Serializer.JSON_TYPE;
        } else if (Serializer.HESSION2_TYPE.getSerializerAlgorithm() == version) {
            return Serializer.HESSION2_TYPE;
        } else if (Serializer.NATIVE_TYPE.getSerializerAlgorithm() == version) {
            return Serializer.NATIVE_TYPE;
        } else {
            return Serializer.NATIVE_TYPE;
        }
    }


}
