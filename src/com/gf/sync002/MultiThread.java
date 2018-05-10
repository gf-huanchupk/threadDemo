package com.gf.sync002;

/**
 * 关键字synchronized 取得的锁都是对象锁 ， 而不是把一段代码（方法）当做锁，
 * 所以代码中哪个线程先执行synchronized关键字的方法 ，哪个线程就持有该方法所属对象的锁（Lock）,
 * 
 * 在静态方法上加synchronized关键字，表示锁定.class类，类一级别的锁（独占 .class类）。
 *
 */
public class MultiThread {

	private static int num = 0;

	public static synchronized void printNum(String tag) {
		try {
			if (tag.equals("a")) {
				num = 100;
				System.out.println("tag a , set num over !");
				Thread.sleep(1000);
			} else {
				num = 200;
				System.out.println("tag b , set num over !");
			}

			System.out.println("tag " + tag + ", num = " + num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 注意观察run 方法输出顺序
	public static void main(String[] args) {

		// 两个不同的对象
		MultiThread m1 = new MultiThread();
		MultiThread m2 = new MultiThread();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				m1.printNum("a");
			}
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				m2.printNum("b");
			}
		}).start();

	}
}
