package com.gf.height.concurrent019;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 是一种基于技术的信号量。
 * 			 他可以是指一个阈值，基于此，多线程竞争获取信号量，做完自己的申请后规还，超过阈值后，线程申请许可信号量将会被阻塞。
 * Semaphore 可以用好构建一些对象池，资源池之类的，比如数据库连接池，
 *           我们也可以创建计数为1的Semaphore ，将其作为一种类似互斥锁的机制。
 * 
 * @author huanchu
 *
 */
public class UseSemphore {

	public static void main(String[] args) {
		//线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		//只能5个线程同时访问
		final Semaphore semp = new Semaphore(2);
		//模拟20个客户端访问
		for(int index = 0 ; index < 20 ; index ++){
			final int NO = index;
			Runnable run = new Runnable() {
				
				@Override
				public void run() {
					
					try {
						//获取许可
						semp.acquire();
						System.out.println("Accessing: " + NO);
						//模拟世纪业务逻辑
						Thread.sleep((long)(Math.random() * 10000));
						// 释放许可
						semp.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			exec.execute(run);
		}
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		exec.shutdown();
	}
	
}
