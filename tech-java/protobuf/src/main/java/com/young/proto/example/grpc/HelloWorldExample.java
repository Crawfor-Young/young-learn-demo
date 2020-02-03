package com.young.proto.example.grpc;

import com.young.proto.client.HelloWorldGrpcClient;
import com.young.proto.server.HelloWorldServer;
import java.io.IOException;

public class HelloWorldExample {

  public static void main(String[] args) {

    Thread serverThread = new Thread(HelloWorldExample::createServer);
    serverThread.start();

    HelloWorldGrpcClient client = createClient();
    mockClientAction(client);
    shutdownClient(client);
    shutdownServer();
  }

  private static void createServer() {
    try {
      HelloWorldServer.deploy();//port:50001
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  private static void shutdownServer() {
    HelloWorldServer.shutdown();
  }

  private static HelloWorldGrpcClient createClient() {
    return new HelloWorldGrpcClient("127.0.0.1", 50051);
  }

  private static void mockClientAction(HelloWorldGrpcClient client) {
    for (int i = 0; i < 5; i++) {
      client.send("World " + i);
    }
  }

  private static void shutdownClient(HelloWorldGrpcClient client) {
    client.shutdown();
  }


}
