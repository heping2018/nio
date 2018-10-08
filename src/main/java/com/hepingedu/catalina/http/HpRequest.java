package com.hepingedu.catalina.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * Created by heping on 2018/10/7.
 */
public class HpRequest {
    private ChannelHandlerContext ctx;
    private HttpRequest request;
    public HpRequest(ChannelHandlerContext ctx,HttpRequest request ){
            this.ctx = ctx;
            this.request = request;
    }
    public  String getUri(){
        return request.getUri();
    }
    public String getMethod(){
        return request.getMethod().name();
    }
    public Map<String,List<String>> getPamamters(){
        QueryStringDecoder decoder = new QueryStringDecoder(request.getUri());
        return decoder.parameters();
    }

    public String getPamamters(String name){
       Map<String,List<String>> pamas = getPamamters();
        List<String> param = pamas.get(name);
        if(null == param){
            return null;
        }else{
            return param.get(0);
        }
    }

}
