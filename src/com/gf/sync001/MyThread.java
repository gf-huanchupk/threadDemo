package com.gf.sync001;

public class MyThread extends Thread{
	
	private int count = 5;
	
	@Override
	public synchronized void run() {
		count--;
		System.out.println(this.currentThread().getName() +" count =" + count);
	}
	

	public static void main(String[] args) {
		/**
		 * 分析：当多线程访问 myThread的 run方法时，以排队的方式进行处理（这里排队是按照CPU分配的先后顺序定的），
		 *      一个线程想要执行synchroined 修饰的方法里的代码：
		 *      1 尝试获得锁
		 *      2 如果拿到锁，执行synchronized代码具体的内容：拿不到锁，这个线程就回不断的尝试获得这把锁，直到拿到为止，
		 *         而且多个线程同时去竞争一把锁（也就会有锁的竞争问题） 
		 */
		MyThread myThread = new MyThread();
		
		Thread t1 = new Thread(myThread , "t1");
		Thread t2 = new Thread(myThread , "t2");
		Thread t3 = new Thread(myThread , "t3");
		Thread t4 = new Thread(myThread , "t4");
		Thread t5 = new Thread(myThread , "t5");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
	}
	
}
