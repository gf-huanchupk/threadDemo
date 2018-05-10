package com.gf.sync007;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile可使变量在多个线程中就有可见性，
 * 但volatile关键字不具备synchronized 关键字的原子性
 * 要实现原子性建议使用atomic类的系列对象（注意atomci类只保证本身方法的原子性，并不保证多次操作的原子性）
 * @author huanchu
 *
 */
public class VolatileNoAtomic extends Thread {
	//private static volatile int count;
	private static AtomicInteger count = new AtomicInteger(0);
	
	public static void addCount(){
		 for (int i = 0; i < 1000; i++) {
			//count++;
			 count.incrementAndGet();
		 }
		 System.out.println(count);
	}
	
	public void run(){
		addCount();
	}
	
	public static void main(String[] args) {
		
		VolatileNoAtomic[] arr = new VolatileNoAtomic[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = new VolatileNoAtomic();
		}
		
		for (int i = 0; i < 10; i++) {
			arr[i].start();
		}
	}
}
