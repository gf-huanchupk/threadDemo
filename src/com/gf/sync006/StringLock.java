package com.gf.sync006;

/**
 * 尽量不要使用 String 的常量加锁，会出现死循环问题。
 * 
 * @author huanchu
 *
 */
public class StringLock {

	public void method() {
		// new Srting ("字符串常量")
		//synchronized ("字符串常量") {
		synchronized (new String("字符串常量")) {
			try {
				while (true) {
					System.out.println("当前线程 ： " + Thread.currentThread().getName() + "开始");
					Thread.sleep(1000);
					System.out.println("当前线程 ： " + Thread.currentThread().getName() + "结束");

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		final StringLock stringLock = new StringLock();
		new Thread(new Runnable() {

			@Override
			public void run() {
				stringLock.method();

			}
		},"t1").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				stringLock.method();

			}
		},"t2").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				stringLock.method();

			}
		},"t3").start();

	}
}
