package com.gf.height.concurrent017;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * newScheduledThreadPool()方法：该方法返回一个ScheduledExecutorService对象，但该线程池可以可以执行线程的数量
 * 
 * @author huanchu
 *
 */
public class ScheduledJob {
	
	public static void main(String[] args) {
		
		Temp command = new Temp();
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		
		ScheduledFuture<?> scheduleTask = scheduler.scheduleWithFixedDelay(
				command, //定时执行的任务
				5, //多长时间后执行
				1, //循环执行的相隔时间
				TimeUnit.SECONDS // 时间单位
				);
		
	}
	
}

class Temp extends Thread{
	@Override
	public void run() {
		System.out.println("run");
	}
}
