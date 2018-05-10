package com.gf.height.design015;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable{
	
	private ConcurrentLinkedQueue<Task> workQueue;

	private ConcurrentHashMap<String,Object> resultMap;
	
	public void setWorkerQueue(ConcurrentLinkedQueue<Task> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	@Override
	public void run() {
		while (true) {
			Task input = this.workQueue.poll();
			if(input == null) break;
			//真正的去做业务处理
			Object output = Myworker.handle(input);
			this.resultMap.put(Integer.toString(input.getId()), output);
		}
	}

	public static Object handle(Task input){
		return null;
	}
	
	/**
	private Object handle(Task input) {
		Object output = null;
		
		try {
			//表示处理Task任务的耗时，可能是数据的加工，也可能是操作数据库...
			Thread.sleep(500);
			output = input.getPrice();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		return output;
	}
	*/
	

}
