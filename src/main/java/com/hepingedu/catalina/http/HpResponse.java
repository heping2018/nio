package com.hepingedu.catalina.http;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.Names.EXPIRES;

/**
 * Created by heping on 2018/10/7.
 */
public class HpResponse {
    private ChannelHandlerContext ctx;
    private HttpRequest r;
    public  HpResponse(ChannelHandlerContext ctx,HttpRequest response){
        this.ctx = ctx;
        this.r = response;
    }
    public  void write(String out) throws  Exception{
        try {
            if(out == null){
                return;
            }
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1
                    , HttpResponseStatus.OK
                    , Unpooled.wrappedBuffer(out.getBytes("UTF-8")));
            response.headers().set(CONTENT_TYPE, "text/json");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(EXPIRES, 0);
            if (HttpHeaders.isKeepAlive(r)) {
                response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);

            }
            ctx.write(response);
        }finally {
            ctx.flush();
        }
    }
}
