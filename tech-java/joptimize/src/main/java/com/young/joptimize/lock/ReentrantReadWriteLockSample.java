package com.young.joptimize.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockSample {

  private double x,y;
  private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  private Lock readLock = lock.readLock();
  private Lock writeLock = lock.writeLock();

  public double read(){
    readLock.lock();
    try {
      return Math.sqrt(x*x+y*y);
    }finally {
      readLock.unlock();
    }
  }

  public void move(double deltaX, double deltaY){
    writeLock.lock();
    try {
      x+= deltaX;
      y+= deltaY;
    }finally {
      writeLock.unlock();
    }
  }
}
