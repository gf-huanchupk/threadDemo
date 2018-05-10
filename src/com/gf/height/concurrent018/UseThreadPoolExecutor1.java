package com.gf.height.concurrent018;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 * 构造方法对于队列是什么类型的比较关键：
 * 在使用有界限队列时：若有新的任务需要执行，如果线程池实际线程数量小于corePoolSize,则优先创建线程，
 *                 若大于corePoolSize，则将任务加入队列，
 *                 若队列已满，则在总线线程数不大于maxximumPoolSzie的前提下，创建新的线程，
 *                 若线程数大于maxximumPoolSize，则执行拒绝策略。或其他的自定义方式。
 *                 
 * 无界队列时：       LinkedBlockingQueue。与有界队列相比，除非系统资源耗尽，否则无界的任务队列不存在任务入队失败的情况。
 *                 当有新的任务到来，系统的线程数小于corePoolSize时，则新建线程执行任务,
 *                 当达到corePoolSize后，就不会继续增加，若后续仍有新的任务加入，而又没有看空闲的线程资源，则任务直接进入队列等候。若任务创建和处理的速度差异很大，无界队列会保持快速增长，直到耗尽系统内存。
 * 
 * JDK拒绝策略：    （方式都不太好，可以之定义拒绝策略，需要实现RejectedExecutionHandler接口）
 *                 AbortPolicy：直接抛出异常组织组织系统正常工作
 *                 CollerRunsPolicy：只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务
 *                 DiscardOldestPolicy：丢弃最老的一个请求，尝试再次提交当前的任务，
 *                 DiscardPolicy：丢弃无法处理的任务，不给予任何处理
 * 
 * @author huanchu
 *
 */
public class UseThreadPoolExecutor1 {

	public static void main(String[] args) {
		
		ThreadPoolExecutor pool = new ThreadPoolExecutor(
				1,//corePoolSize 核心线程数量
				2,//maximumPoolSize 线程池维护线程的最大数量
				60,//keepAliveTime 线程池维护线程所允许的空闲时间
				TimeUnit.SECONDS,//unit 时间单位
				new ArrayBlockingQueue<Runnable>(3)//workQueue 线程池所使用的缓冲队列
				//handler 线程池对拒绝任务的处理策略
				);
		
		MyTask mt1 = new MyTask(1, "任务1");
		MyTask mt2 = new MyTask(2, "任务2");
		MyTask mt3 = new MyTask(3, "任务3");
		MyTask mt4 = new MyTask(4, "任务4");
		MyTask mt5 = new MyTask(5, "任务5");
		MyTask mt6 = new MyTask(6, "任务6");
		
		pool.execute(mt1);
		pool.execute(mt2);
		pool.execute(mt3);
		pool.execute(mt4);
		pool.execute(mt5);
		pool.execute(mt6);
		
		pool.shutdown();
		
	}
	
}
