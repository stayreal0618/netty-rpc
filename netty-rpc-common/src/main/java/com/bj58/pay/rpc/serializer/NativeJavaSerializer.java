package com.bj58.pay.rpc.serializer;

import com.bj58.pay.rpc.constants.SerializeVersion;

import java.io.*;

/**
 * @author stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/12/9 上午10:45
 */
public class NativeJavaSerializer implements Serializer{

    public byte getSerializerAlgorithm() {
        return SerializeVersion.NATIVE_JAVA;
    }

    public byte[] serialize(Object object) {

        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream outputStream = null;

        try {

            byteArrayOutputStream = new ByteArrayOutputStream();
            outputStream = new ObjectOutputStream(byteArrayOutputStream);

            outputStream.writeObject(object);

            return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {

            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);

            return (T) objectInputStream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }

                if (objectInputStream != null) {
                    objectInputStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;

    }
}
