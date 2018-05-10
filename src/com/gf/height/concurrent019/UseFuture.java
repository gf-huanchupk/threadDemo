package com.gf.height.concurrent019;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Callable接口类似Runnable,从名字就可以看出来了，
 *         但是Runnable不会返回结果，并且无法抛出返回结果的异常，
 *         而Callable功能更强大一些，被线程执可以返回值，这个返回值可以被Future你拿到，
 *         也就是说，Future可以拿到异步执行任务的返回值。
 *         Future模式非常合适在处理耗时很长的业务逻辑时进行使用，可以有效的减小系统的响应时间，提高系统的吞吐量。
 * 
 * @author huanchu
 *
 */
public class UseFuture implements Callable<String>{

	private String para;
	
	public UseFuture(String para) {
		this.para = para;
	}
	
	
	/**
	 * 这里是真实的业务逻辑，其执行可能很慢
	 */
	@Override
	public String call() throws Exception {
		//模拟执行耗时
		Thread.sleep(5000);
		String result = this.para + "处理完成";
		
		return result;
	}
	
	//主控制函数
	public static void main(String[] args) throws Exception {
		String queryStr = "query";
		//构造FutureTask，并且传入需要真正进行业务逻辑处理的类，该类一定要实现了Callable接口的类
		FutureTask<String> future = new FutureTask<>(new UseFuture(queryStr));
		FutureTask<String> future2 = new FutureTask<>(new UseFuture(queryStr));
		
		//创建一个固定线程的线程池且线程数为1，
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		//这里提交任务future，则开启线程执行RealData的call()方法执行
		//submit和execute的区别：1.submit可以传入实现Callable接口的实例对象，第二点是submit方法是有返回值的
		
		Future f1 = executor.submit(future);
		Future f2 = executor.submit(future2);
		
		try {
			//这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
			System.out.println("处理实际的业务逻辑...");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("等待Future 数据...");
		
		//调用获取数据方法，如果call()方法没有执行完成，则依然会进行等待
		System.out.println("数据：" + future.get());
		System.out.println("数据：" + future2.get());
		
		executor.shutdown();
	}

}
