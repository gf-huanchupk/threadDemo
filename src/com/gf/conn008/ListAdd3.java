package com.gf.conn008;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author huanchu
 *
 */
public class ListAdd3 {
	
	private volatile static List list = new ArrayList();
	
	public void add(){
		list.add("bjsxt");
	}
	
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		
		ListAdd3 list3 = new ListAdd3();
		
		final CountDownLatch countDownLatch = new CountDownLatch(1);// 参数是通知的次数
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					for(int i = 0 ; i < 10 ; i++){
						list3.add();
						System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
						Thread.sleep(500);
						if(list.size() == 5){
							System.out.println("已经发出通知..");
							countDownLatch.countDown();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				if(list3.size() != 5){
					try {
						countDownLatch.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止了");
				throw new RuntimeException();
				}
		},"t2");
		
		t2.start();
		t1.start();
		
	}
	
	
}
