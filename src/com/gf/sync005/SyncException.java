package com.gf.sync005;

/**
 * 说明：对于web应用程序，异常释放锁的情况，如果不及时处理，很有可能对你的应用程序业务产生严重的错误，
 *      比如你现在执行一个队列任务，很多对象都去等待第一个对象正确执行完毕再去释放锁，
 *      但是第一个对象由于异常的出现没导致业务逻辑没有正常的执行完毕，就释放可锁，那么可想而知后续的对象执行的都是错误的逻辑。
 *      所以这一点一定要引起注意，在编写代的时候，一定要考虑周全。
 * @author huanchu
 *
 */
public class SyncException {
	
	private int i = 0;
	public synchronized void operation(){
		while(true){
			try {
				i++;
				Thread.sleep(200);
				System.out.println(Thread.currentThread().getName() + " , i = " + i);
				if(i == 10){
					Integer.parseInt("a");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(" log info i = " + i);
				//throw new RuntimeException();
				//continue;
			}
		}
	}
	
	public static void main(String[] args){
		
		final SyncException se = new SyncException();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				se.operation();
			}
		}).start();
		
	}
	
	
}
