package com.gf.height.design016;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

	private BlockingQueue<Data> queue;
	
    public Consumer(BlockingQueue<Data> queue) {
		this.queue = queue; 
	}
	
    //随机对象
    private static Random r = new Random();
    
	@Override
	public void run() {
		while(true){
			try {
				//获取数据
				Data data = this.queue.take();
				//进行数据的处理，休眠 0-100毫秒 模拟耗时
				Thread.sleep(r.nextInt(1000));
				System.out.println("当前消费线程：" + Thread.currentThread().getName() + "， 消费成功，消费数据的id为：" + data.getId());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
