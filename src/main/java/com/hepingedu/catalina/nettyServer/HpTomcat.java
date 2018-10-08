package com.hepingedu.catalina.nettyServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;


/**
 * Created by heping on 2018/10/7.
 */
public class HpTomcat {
    public void start(int port) throws Exception{

/*nio
  ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind()*/
//bio
        /*ServerSocket s = new ServerSocket(port);*/
        //netty
        /*
        *BOSS线程
         */
        EventLoopGroup bossGroup = null;
        EventLoopGroup workGroup = null;
        try {
             bossGroup = new NioEventLoopGroup();
        /*
        *work线程
         */
             workGroup = new NioEventLoopGroup();
            //netty服务
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //主从县城模型
            ServerBootstrap serverBootstrapbossslave = serverBootstrap.group(bossGroup, workGroup)
                    //反射，主线的处理类
                    .channel(NioServerSocketChannel.class)
                            //当有客户端连进来的时候，所触发的方法，子线程处理类
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override//
                        protected void initChannel(SocketChannel cilent) throws Exception {
                            //无锁华串行编程
                            //业务逻辑链路  编码器
                            cilent.pipeline().addLast(new HttpRequestEncoder());
                            //解码器
                            cilent.pipeline().addLast(new HttpRequestDecoder(   ));
                            //业务逻辑处理
                            cilent.pipeline().addLast(new HepTomcatHanler());


                        }
                        //让主线程不停止，配置信息，针对主线程的配置,128是分配线程的数量
                    }).option(ChannelOption.SO_BACKLOG, 128)
                            //针对子线程的配置  长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            //阻塞
            ChannelFuture f = serverBootstrapbossslave.bind(port).sync();//等待
            System.out.println("hptomcat 已经启动");
            f.channel().closeFuture().sync();
        }finally {
            if (bossGroup != null) {
                bossGroup.shutdownGracefully();
            }
            if (workGroup != null) {
                workGroup.shutdownGracefully();

            }
        }
    }

    public static void main(String[] args) {
        try {
          new HpTomcat().start(8080);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
