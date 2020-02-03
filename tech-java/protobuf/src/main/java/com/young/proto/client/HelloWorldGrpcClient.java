package com.young.proto.client;

import com.young.proto.service.GrpcHelloWorldService;
import com.young.proto.service.HelloWorldGrpc;
import com.young.proto.service.HelloWorldGrpc.HelloWorldBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.concurrent.TimeUnit;

public class HelloWorldGrpcClient {

  private final ManagedChannel channel;
  private final HelloWorldBlockingStub blockingStub;

  public HelloWorldGrpcClient(String host, int port) {
    channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    blockingStub = HelloWorldGrpc.newBlockingStub(channel);
  }

  public void shutdown() {
    //channel.shutdown().awaitTermination(5,TimeUnit.SECONDS);
    channel.shutdown();
  }

  public void send(String name){
    GrpcHelloWorldService.HelloRequest request = GrpcHelloWorldService.HelloRequest.newBuilder().setName(name).build();
    GrpcHelloWorldService.HelloReply reply = blockingStub.sayHello(request);
    System.out.println(reply.getMessage());
  }

  public static void main(String[] args) {
    HelloWorldGrpcClient client = new HelloWorldGrpcClient("127.0.0.1",50051);
    for (int i = 0; i < 5; i++) {
      client.send("World "+i);
    }
    client.shutdown();
  }

}
