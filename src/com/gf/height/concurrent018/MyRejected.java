package com.gf.height.concurrent018;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自己定义决绝策略
 * 
 * @author huanchu
 *
 */
public class MyRejected implements RejectedExecutionHandler{

	public MyRejected() {
	}

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("自定义处理..");
		System.out.println("当前被拒绝发任务为： " + r.toString());
	}
	
	
	
}
