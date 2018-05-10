package com.gf.sync006;

/**
 * 锁对象的改变问题，当使用一个对象进行加锁的时候，要注意对象本身发生改变的时候，那么持有的锁就不同了。
 * 但如果对象本身不发生改变，那么依然是同步的，即使是对象的属性发生了改变
 * 
 * @author huanchu
 *
 */
public class ChangeLock {

	public String lock = "lock";
	
	public void method() {
		synchronized (lock) {
			try {
				System.out.println("当前线程 ： " + Thread.currentThread().getName() + "开始");
				lock = "change lock";
				Thread.sleep(30000);
				System.out.println("当前线程 ： " + Thread.currentThread().getName() + "结束");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		final ChangeLock stringLock = new ChangeLock();
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
