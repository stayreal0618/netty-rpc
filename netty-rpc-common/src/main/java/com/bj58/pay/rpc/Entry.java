package com.bj58.pay.rpc;

import java.io.Serializable;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/11/28 下午11:13
 */
public class Entry<K, V> implements Serializable {

    private static final long serialVersionUID = 2755783510760788555L;
    private K k;
    private V v;

    public Entry(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getKey() {
        return k;
    }

    public V getValue() {
        return v;
    }


}
