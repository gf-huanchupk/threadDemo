package com.gf.height.concurrent019;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseCyclicBarrier {

	static class Runner implements Runnable{
		
		private CyclicBarrier barrier;
		
		private String name;
		

		public Runner(CyclicBarrier barrier, String name) {
			this.barrier = barrier;
			this.name = name;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(1000 * (new Random()).nextInt(5));
				System.out.println(name + " 准备OK");
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			} 
			
			System.out.println(name + " GO！！");
		}
		
	}
	
	
	public static void main(String[] args)  throws IOException , InterruptedException{
		CyclicBarrier barrier = new CyclicBarrier(2);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		executor.execute(new Thread(new Runner(barrier, "zhangsan")));
		executor.execute(new Thread(new Runner(barrier, "lisi")));
		executor.execute(new Thread(new Runner(barrier, "wangwu")));
		
		
		executor.shutdown();
		
		
	}
	
}
