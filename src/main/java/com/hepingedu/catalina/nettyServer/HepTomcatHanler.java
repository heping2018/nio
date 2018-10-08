package com.hepingedu.catalina.nettyServer;

import com.hepingedu.catalina.http.HpRequest;
import com.hepingedu.catalina.http.HpResponse;
import com.hepingedu.catalina.servlets.MyServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

/**
 * Created by heping on 2018/10/7.
 */
public class HepTomcatHanler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest){
            HttpRequest r = (HttpRequest)msg;
            HpRequest request = new HpRequest(ctx,r);
            HpResponse response = new HpResponse(ctx,r);

            MyServlet.class.newInstance().doGet(request,response);


        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
