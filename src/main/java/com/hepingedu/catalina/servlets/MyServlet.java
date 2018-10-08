package com.hepingedu.catalina.servlets;

import com.hepingedu.catalina.http.HpRequest;
import com.hepingedu.catalina.http.HpResponse;
import com.hepingedu.catalina.http.HpServerlet;

/**
 * Created by heping on 2018/10/7.
 */
public class MyServlet extends HpServerlet {
    @Override
    public void doGet(HpRequest request, HpResponse response) {
        System.out.println("接收到了");
        try {

            response.write(request.getPamamters("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doPost(HpRequest request, HpResponse response) {

    }
}
