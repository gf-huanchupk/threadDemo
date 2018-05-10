package com.gf.height.concurrent018;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class UseThreadPoolExecutors2 implements Runnable{

	private static AtomicInteger count = new AtomicInteger(0);

	@Override
	public void run() {
		
		try {
			int temp = count.incrementAndGet();
			System.out.println("任务" + temp);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		//System.err.println(Runtime.getRuntime().availableProcessors());
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(15);
		ExecutorService executor =  new ThreadPoolExecutor(
				5,//corePoolSize, 
				10,//maximumPoolSize, 
				120,//keepAliveTime, 
				TimeUnit.SECONDS,//unit, 
				queue//workQueue
				);
		
		for (int i = 0; i < 20; i++) {
			executor.execute(new UseThreadPoolExecutors2());
		}
		
		Thread.sleep(1000);
		System.out.println("queue size:" + queue.size());
		Thread.sleep(2000);
		
		executor.shutdown();
		
	}
	
}
