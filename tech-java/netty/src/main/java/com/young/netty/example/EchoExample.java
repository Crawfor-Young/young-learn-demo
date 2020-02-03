package com.young.netty.example;

import com.young.netty.client.EchoClient;
import com.young.netty.server.EchoServer;

public class EchoExample {

  public static void main(String[] args) throws InterruptedException {
    Thread serverThread = new Thread(()-> {
      try {
        new EchoServer(50001).start();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    serverThread.start();
    new EchoClient("127.0.0.1", 50001).start();
    Thread.sleep(1000);
  }
}
