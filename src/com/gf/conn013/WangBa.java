package com.gf.conn013;

import java.util.concurrent.DelayQueue;

/**
 * DelayQueue: 带有延迟时间的Queue，其中的元素只有当其指定的延迟时间到了，才能够从队列中获取改元素
 *             DelayQueue中的元素必须实现Delay接口，
 *             DelayQueue是一个没有大小限制的队列，
 *             应用场景很多，比如对缓存超时的数据进行移除、任务超时处理、空闲连接的关闭等等
 * 
 * @author huanchu
 *
 */
public class WangBa implements Runnable{
	
	
	private DelayQueue<Wangmin> queue = new DelayQueue<Wangmin>();
	
	private boolean yinye = true;
	
	public void shangji(String name , String id , int money){
		Wangmin man = new Wangmin(name , id , 1000 * money + System.currentTimeMillis());
		
		System.out.println("网名"+man.getName() + " 身份证" + man.getId() + " 交钱" + money + "块 ，开始上机了...");
		
		this.queue.add(man);
	}
	
	public void xiaji(Wangmin man){
		System.out.println("网民"+ man.getName() + "下机了...");
	}
	
	@Override
	public void run() {
		while (yinye){
			try {
				Wangmin man = queue.take();
				xiaji(man);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("网吧开始营业");
			
			WangBa siyu = new WangBa();
			
			new Thread(siyu).start();
			
			siyu.shangji("路人甲", "123", 1);
			siyu.shangji("路人乙", "234", 10);
			siyu.shangji("路人丙", "345", 5);
			siyu.shangji("路人丁", "456", 20);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
