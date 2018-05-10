package com.gf.conn013;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 
 * PriorityBlockingQueue: 基于优先级的阻塞队列（优先级的判断通过构造函数传入的的Compator对象决定，也就是传入的队列的对象必须实现Comparable接口）
 *                        在实现PriorityBlockiongQueue时，内部控制线程同步的锁采用的是公平锁
 *                        它也是一个无界队列
 *                        虽然遍历队列依然是，放入的顺序 ，但是在通过take()方法获取数据时，获取的是通过比较规则排列的数据
 * 
 * @author huanchu
 */
public class UsePriorityBlockingQueue {

	public static void main(String[] args) throws InterruptedException {
		
		PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<Task>();
		
		Task t1 = new Task();
		t1.setId(3);
		t1.setName("任务1 ，id=3");
		
		Task t2 = new Task();
		t2.setId(6);
		t2.setName("任务2 ，id=6");
		
		Task t3 = new Task();
		t3.setId(1);
		t3.setName("任务3 ，id=1");
		
		q.add(t1);
		q.add(t2);
		q.add(t3);
		
		//直接for循环变量，队列中的元素是不会按我们想要顺序遍历出来
//		for (Iterator iterator = q.iterator(); iterator.hasNext();) {
//			Task task = (Task) iterator.next();
//			System.out.println(task.getName());
//			
//		}
		q.take();//每次取出的元素，则是站在我们的的比较规则排列的顺序，取出元素
		
	}
	
}
