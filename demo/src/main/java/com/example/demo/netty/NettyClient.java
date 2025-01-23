package com.example.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //客户端启动类
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder()); //添加解码器
                            pipeline.addLast(new StringEncoder()); //添加编码器
                            pipeline.addLast(new SimpleChannelInboundHandler<String>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                    System.out.println("接收到服务端消息: " + msg);
                                }
                            });
                        }
                    });

            System.out.println("Netty 客户端启动中...");
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            Channel channel = channelFuture.channel();
            channel.writeAndFlush("你好, Netty 服务端! ");
            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
