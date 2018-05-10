package com.gf.conn013;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ArrayBlockingQueue:    基于属数组的阻塞队列实现，在ArrayBlockingQueue内部，
 *                        维护了一个定长的数组，以便缓冲队列中的数据对象，
 *                        其内部没有实现先读写分离，也就意味着生产和消费不能完全并行，长度是需要定义的，可以指定先进先出或先进后出
 *                        也叫做有界队列，在很多场合非常适合使用
 * LinkedBlockingQueue:   基于链表的阻塞队列，同ArrayBlockingQueue类似，
 *                        其内部也维持着一个数据缓冲队列（该队列由一个链表构成），
 *                        LinkBlocking之所以能够高效的处理并发数据，是因为内部采用分离锁（读写分离两个锁），从而实现生产者和消费者操作的完全并发运行
 *                        他是一个无界队列
 * SynchronousQoueue：    一种没有缓冲的队列，生产者生产的数据直接被消费者获取并消费
 * 
 * 
 * 
 * @author huanchu
 *
 */
public class UseOueue {

	public static void main(String[] args) throws Exception {
		
		//高性能无阻塞无界队列：ConcurrentLinkedQueue
		/**
		ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.add("e");
		
		System.out.println(q.poll()); //a 从头部去除元素 ，并从队列里删除
		System.out.println(q);        //b, c, d, e
		System.out.println(q.peek()); //b 从头部去除元素 ，并从队列里删除
		System.out.println(q);        //b, c, d, e
		*/
		
		//有界队列
		/**
		ArrayBlockingQueue<String> array = new ArrayBlockingQueue<>(5);
		array.put("a");
		array.put("b");
		array.add("c");
		array.add("d");
		array.add("e");
		array.add("f");
		*/
		
		//阻塞队列
		
		LinkedBlockingQueue<String> q = new LinkedBlockingQueue<String>(6);
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.offer("e");
		q.add("f");
		q.add("g");
		
		//System.out.println(q);
		
		for (Iterator iterator = q.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		
		List<String> list = new ArrayList<String>();
		//q.drainTo(list);    // 取出队列中的所有元素，放入list中
		//q.drainTo(list, 3); // 在队列中取出指定数量的元素放入到list中
		
		
	}
	
}
