package com.gf.height.concurrent017;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.gf.sync007.RunThread;

/**
 * 为了更好的控制多线程，JDK提供了一套线程框架Executor,帮助开发人员有效的进行线程控制。
 * 它们都在java.util.concurrent包中，是JDK并发包的核心。
 * 其中有一个比较重要类：Executors，它扮演着线程工厂的角色，我们通过Executors创建线程池方法：
 * 
 * newFixedThreadPool()方法：该方法返回一个固定数量的线程池，该方法的线程数量始终不变，
 * 当有一个任务提交时，若线程池中空闲，则立即执行，若没有，则会被暂缓在一个任务队列中，等到有空闲的线程去执行。
 * 
 * newSingleThreadExecutor()方法：该方法创建一个线程的线程池，若空闲则执行，若没有空闲线程则暂缓在任务队列中 (不常用)
 * 
 * newCachedThreadPool()方法：返该方法返回一个根据实际情况调整线程个数的线程池，
 * 不限制最大线程的数量，若有空闲的线程执行任务，若无任务则不创建线程。并且每一个空闲线程都会在60秒后自动回收。
 * 
 * newScheduledThreadPool()方法：该方法返回一个ScheduledExecutorService对象，但该线程池可以可以执行线程的数量
 * 
 * 
 * @author huanchu
 *
 */
public class UseExecutors {

	public static void main(String[] args) {
		//ExecutorService pool = Executors.newSingleThreadExecutor();
		
	}
	
}
