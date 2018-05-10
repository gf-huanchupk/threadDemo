package com.gf.height.lock20;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * RentrantLock：重入锁，在进行同步的代码部分加上锁定，但不要忘记最后一定要释放锁定，不然会造成锁永久无法释放
 * 
 * @author huanchu
 *
 */
public class UseRentrantLock {

	private Lock lock = new ReentrantLock();
	
	public void method1(){
		try {
			lock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进入method1..");
			Thread.sleep(1000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "退出method1..");
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void method2(){
		try {
			lock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进入method2..");
			Thread.sleep(2000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "退出method2..");
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final UseRentrantLock ur = new UseRentrantLock();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ur.method1();
				ur.method2();
			}
		},"t1");
		
		t1.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
