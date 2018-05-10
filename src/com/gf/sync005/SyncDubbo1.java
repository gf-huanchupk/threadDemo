package com.gf.sync005;

/**
 * synchronized 锁重入
 * 关键字synchronize拥有锁对象的重入的功能，也就是在使用synchronized时，当一个线程得到一个对象的锁后，再次请求此对象是可以再次获得改对象的锁
 * @author huanchu
 *
 */
public class SyncDubbo1 {
	
	public synchronized void method1(){
		System.out.println("method1..");
		method2();
	}
	
	public synchronized void method2(){
		System.out.println("method2..");
		method3();
	}
	
	public synchronized void method3(){
		System.out.println("method3..");
	}
	
	public static void main(String[] args) {
		final SyncDubbo1 sd = new SyncDubbo1();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				sd.method1();
			}
		}).start();
	}
}
