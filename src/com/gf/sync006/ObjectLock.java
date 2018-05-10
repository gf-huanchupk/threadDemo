package com.gf.sync006;

/**
 * synchronized 可以使用任意的Object进行加锁，用法比较灵活
 * @author huanchu
 *
 */
public class ObjectLock {

	public void method1() {
		synchronized (this) { // 对象锁
			try {
				System.out.println("do method1..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void method2() {
		synchronized (ObjectLock.class) { // 类锁
			try {
				System.out.println("do method2..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Object lock = new Object();

	public void method3() {
		synchronized (lock) {// 任何对象多
			try {
				System.out.println("do method3..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		final ObjectLock objectLock = new ObjectLock();
		new Thread(new Runnable() {

			@Override
			public void run() {
				objectLock.method1();
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				objectLock.method2();
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				objectLock.method3();
			}
		}).start();
	}

}
