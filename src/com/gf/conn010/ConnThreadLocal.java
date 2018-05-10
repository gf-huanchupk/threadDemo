package com.gf.conn010;

/**
 * ThreadLocal概念：线程局部变量，是一种多线程间并发访问变量的解决方案。于其synchronized等加锁的方式不同，
 * ThreadLocal完全不提供锁，而是使用以空间换时间的手段，为每个线程提供变量的独立副本，以保证线程安全。
 * 从性能上说，ThreadLocal不具有绝对的优势，在并发量或者竞争激烈的场景，使用ThreadLocal可以在一定程度上减少锁竞争。
 * 示例解释：
 *       虽然th是静态的全局变量，但是线程1对th赋值后，只能在线程一种访问带th的属性值，线程2访问th，th就是null
 * @author huanchu
 *
 */
public class ConnThreadLocal {

	public static ThreadLocal<String> th = new ThreadLocal<String>();

	public void setTh(String value){
		th.set(value);
	}
	public void getTh(){
		System.out.println(Thread.currentThread().getName() + ":" + this.th.get());
	}
	

	public static void main(String[] args) {
		
		final ConnThreadLocal ct = new ConnThreadLocal();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ct.setTh("张三");
				ct.getTh();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
//				try {
//					Thread.sleep(1000);
//					ct.getTh();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				ct.getTh();
			}
		},"t2");
		
		t1.start();
		t2.start();
		
	}
	
	
}
