package com.bj58.pay.rpc.serializer;

import com.bj58.pay.rpc.constants.SerializeVersion;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/12/7 下午5:18
 */
@Slf4j
public class Hessian2Serializer implements Serializer{

    public byte getSerializerAlgorithm() {
        return SerializeVersion.HESSIAN;
    }

    public byte[] serialize(Object object) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(byteArrayOutputStream);

        try {
            hessian2Output.writeObject(object);
            hessian2Output.flush();
            hessian2Output.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            log.error("Hessian2Serializer serialize exception", e);
        }

        return null;

    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        Hessian2Input hessian2Input = new Hessian2Input(new ByteArrayInputStream(bytes));
        try {
            return (T) hessian2Input.readObject(clazz);
        } catch (IOException e) {
            log.error("Hessian2Serializer deserialize exception", e);
        }

        return null;
    }
}
