package com.young.proto.server;

import com.young.proto.service.impl.HelloWorldGrpcImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class HelloWorldServer {

  private final static int port = 50051;
  private final static Server server = ServerBuilder.forPort(port).addService(new HelloWorldGrpcImpl())
      .build();

  private HelloWorldServer(){}


  private static void start() throws IOException {
    server.start();
    System.err.println(server.isShutdown());
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.err.println("*** shutting down gRPC server since JVM is shutting down");
      server.shutdownNow();//正在处理和之后到达的请求都不处理直接终止（也不一定会立刻到达Terminated状态）
      System.err.println("*** server shut down");
      System.err.println(server.isShutdown());//可能仍有请求在处理或在释放资源，但不接收新请求
      System.err.println(server.isTerminated());//完全终止，无请求在处理，资源也释放完毕
    }));

  }

  private static void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  public static void deploy() throws IOException, InterruptedException{
    start();
    System.err.println("*** server blocks start");
    blockUntilShutdown();
    System.err.println("*** server blocks over");
  }

  public static void shutdown(){
    server.shutdownNow();
  }


  public static void main(String[] args) throws IOException, InterruptedException {
    start();
    System.err.println("*** server blocks start");
    new Thread(()->{
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      server.shutdown();//不处理之后到达的请求但会继续处理完已接收的请求再终止
    }).start();
    blockUntilShutdown();
    System.err.println("*** server blocks over");
  }

}
