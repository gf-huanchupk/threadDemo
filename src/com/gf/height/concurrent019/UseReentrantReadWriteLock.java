package com.gf.height.concurrent019;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 读写锁ReentrantReadWriteLock,其核心就是实现读写分离的锁。 在高并发访问下，尤其是读多写少的情况，性能远高于重入锁
 * 
 * 之前学习的synchronized、ReentrantLock时，我们知道，同一时间内，只能有一个线程进行访问被锁定的代码，
 * 那么读写锁则不同，其本质是分成两个锁，即读锁、写锁， 在读锁下，多个线程可以并发的进行访问，当是在写锁的时候，只能一个一个的顺序访问
 * 
 * 口诀：读读共享，写写互斥，读写互斥
 * 
 * @author huanchu
 *
 */
public class UseReentrantReadWriteLock {

	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	private ReadLock readLock = rwLock.readLock();
	private WriteLock writeLock = rwLock.writeLock();

	public void read() {

		try {
			readLock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进入...");
			Thread.sleep(3000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "退出...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			readLock.unlock();
		}
	}

	public void write() {
		try {
			writeLock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进入...");
			Thread.sleep(3000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "退出...");

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}
	}

	public static void main(String[] args) {

		final UseReentrantReadWriteLock urrw = new UseReentrantReadWriteLock();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				urrw.read();
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				urrw.read();
			}
		}, "t2");
		
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				urrw.write();
			}
		}, "t3");
		
		Thread t4 = new Thread(new Runnable() {

			@Override
			public void run() {
				urrw.write();
			}
		}, "t4");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();

	}

}
