package com.gf.height.lock20;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 我们在使用synchronized的时候，如果需要多线程间进行协作工作则需要Object的wait()和notify()、notifyAll()
 * 方法进行配合工作。
 * 
 * 我们在使用lock的时候，可以使用一个新的等待/通知的类，它就是Condition。
 * 这个Condition一定是针对具体某一把锁的基础之上才会产生Condition
 * 
 * 公平锁和非公平锁：Lock lock = new ReentrantLock(boolean isFair);默认是非公平
 * lock用法：
 *        tryLock(): 尝试获的锁，获得结果用true/false放回。
 *        tryLock(): 在给定的时间内尝试获得锁，获得结果用true/false返回
 *        isFair: 是否是公平锁 ， true：公平锁
 *        isLocked: 是否锁定
 *        getHoldCount(): 查询当前线程保持此锁的个数
 *        lockInterruptibly(): 有优先响应中断的锁
 *        getQueueLength(): 返回正在等待获取次次锁定的线程数
 *        getWaitQueueLength(): 返回等待与锁定相关给定条件Condition的线程数
 *        hasQueueThread(Thread thread): 查询指定的线程是否正在等待此锁
 *        hasQueueThreads(): 查询是否有线程正在等待次锁
 *        hasWaiters(): 查询是否有线程正在等待与此锁定有关的condition条件
 *        
 * 
 * @author huanchu
 *
 */
public class UseCondition {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void method1() {

		try {
			lock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进入等待状态...");
			Thread.sleep(3000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "释放锁...");
			condition.await();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "继续执行...");

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void method2() {
		try {
			lock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进入...");
			Thread.sleep(3000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "发起唤醒..");
			condition.signal(); //唤醒一个处于等待的线程，但不会释放锁
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {

		final UseCondition uc = new UseCondition();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				uc.method1();
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				uc.method2();
			}
		}, "t2");
		
		t1.start();
		t2.start();

	}

}
