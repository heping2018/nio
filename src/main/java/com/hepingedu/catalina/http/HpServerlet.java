package com.hepingedu.catalina.http;

/**
 * Created by heping on 2018/10/7.
 */
public abstract class HpServerlet {
    public abstract void doGet(HpRequest request,HpResponse response);
    public abstract void doPost(HpRequest request,HpResponse response);
}
