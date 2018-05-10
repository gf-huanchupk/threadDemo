package com.gf.height.concurrent019;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch: 它允许一个或多个线程一直等待，直到其他线程的操作执行完后再执行
 * 常用于监听某些初始化操作，等初始化操作完毕后，通知主线程继续工作
 * 
 * @author huanchu
 *
 */
public class UseCountDownLatch {

	public static void main(String[] args) {
		final CountDownLatch countDown = new CountDownLatch(2);

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					System.out.println("进入线程t1" + "等待其他其他线程处理完成...");
					countDown.await();
					System.out.println("t1线程继续执行...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					System.out.println("t2线程进行初始化操作...");
					Thread.sleep(3000);
					System.out.println("t2线程初始化完毕，通知t1线程继续执行...");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "t2");

		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					System.out.println("t3线程进行初始化操作...");
					Thread.sleep(4000);
					System.out.println("t3线程初始化完毕，通知t1线程继续执行...");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "t3");
		
		t1.start();
		t2.start();
		t3.start();

	}

}
