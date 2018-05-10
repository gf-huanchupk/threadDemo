package com.gf.conn008;

import java.util.ArrayList;
import java.util.List;

/**
 * wait notifys 方法，wait释放锁，notify不释放锁
 * @author huanchu
 *
 */
public class ListAdd2 {
	
	private volatile static List list = new ArrayList();
	
	public void add(){
		list.add("bjsxt");
	}
	
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		
		ListAdd2 list2 = new ListAdd2();
		
		// 1 实例化出一个lock
		// 当使用wait 和 notify的时候 , 一定要配合synchronized关键字
		final Object lock = new Object();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					synchronized (lock) {
						for(int i = 0 ; i < 10 ; i++){
							list2.add();
							System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
							Thread.sleep(500);
							if(list.size() == 5){
								System.out.println("已经发出通知..");
								lock.notify();
							}
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
				synchronized (lock) {
					if(list2.size() != 5){
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止了");
					throw new RuntimeException();
				}
			}
		},"t2");
		
		t2.start();
		t1.start();
		
	}
	
	
}
