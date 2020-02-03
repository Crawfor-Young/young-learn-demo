package com.young.netty.client;

import com.young.netty.client.handler.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;


public class EchoClient {
   //OIO
  private final String host;
  private final int port;

  public EchoClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public void start() throws InterruptedException {
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(group)
          .channel(NioSocketChannel.class)
          .remoteAddress(new InetSocketAddress(host,port))
          .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
              ch.pipeline().addLast(new EchoClientHandler());
            }
          });
      ChannelFuture future = bootstrap.connect().sync();//链接到远程节点，并阻塞等待直到链接完成
      future.channel().closeFuture().sync();//阻塞直到channel关闭
    }finally {
      group.shutdownGracefully().sync();//关闭线程池并且释放所有的资源
    }
  }

  public static void main(String[] args) throws InterruptedException {
    new EchoClient("127.0.0.1",50001).start();
  }

}
