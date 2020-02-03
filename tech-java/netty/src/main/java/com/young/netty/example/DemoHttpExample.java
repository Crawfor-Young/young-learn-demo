package com.young.netty.example;

import com.young.netty.server.DemoHttpServer;

public class DemoHttpExample {
  public static void main(String[] args) throws InterruptedException {
    new DemoHttpServer(50001,false).start();
  }

}
