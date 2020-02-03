package com.young.joptimize.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionSample {

  public static void main(String[] args) throws InterruptedException {
    condition();
  }

  private static ReentrantLock lock = new ReentrantLock();

  private static void condition() throws InterruptedException {
    /*
     * 可以创建多个condition对象
     */
    Condition condition = lock.newCondition();
    Condition condition1 = lock.newCondition();
    lock.lock();
    condition.await(1000,TimeUnit.MILLISECONDS);
    condition.signal();
    lock.unlock();
  }

}
