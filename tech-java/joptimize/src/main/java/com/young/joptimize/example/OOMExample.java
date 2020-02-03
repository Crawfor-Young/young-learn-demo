package com.young.joptimize.example;

import com.young.joptimize.entity.OOMObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OOMExample {

  public static void fillHeap(int num) throws InterruptedException {
    List<OOMObject> list = new ArrayList<>();
    for (int i = 0; i < num ; i++) {
      Thread.sleep(50);
      list.add(new OOMObject());
    }
    System.gc();
    Thread.sleep(50000);
  }

  public static void main(String[] args) throws Exception{
    //fillHeap(2000);
    BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10);
    ThreadPoolExecutor executor = new ThreadPoolExecutor(3,6,1000L,
        TimeUnit.MILLISECONDS,workQueue);
    executor.prestartAllCoreThreads();//启动全部核心线程
    executor.prestartCoreThread();//启动一个核心线程
  }

}
