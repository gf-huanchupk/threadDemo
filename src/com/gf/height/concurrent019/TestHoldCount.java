package com.gf.height.concurrent019;

import java.util.concurrent.locks.ReentrantLock;
/**
 * getHoldCount(): 查询当前线程保持锁定的个数
 * @author huanchu
 *
 */
public class TestHoldCount {

	//重入锁
	private ReentrantLock lock = new ReentrantLock();
	
	public void m1(){
		try {
			lock.lock();
			System.out.println("进入m1方法，holdCount数为：" + lock.getHoldCount());
			
			//调用m2方法
			m2();
			System.out.println("holdCount数为：" + lock.getHoldCount());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
			System.out.println("holdCount数为：" + lock.getHoldCount());
		}
	}
	
	public void m2(){
		try {
			lock.lock();
			System.out.println("进入m2方法，holdCount数为：" + lock.getHoldCount());
			m3();
			System.out.println("holdCount数为：" + lock.getHoldCount());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void m3(){
		try {
			lock.lock();
			System.out.println("进入m3方法，holdCount数为：" + lock.getHoldCount());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		TestHoldCount thc = new TestHoldCount();
		thc.m1();
	}
	
}
