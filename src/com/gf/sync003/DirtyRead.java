package com.gf.sync003;

/**
 * 对于对象的同步和异步的犯法，我们在设计自己的程序的时候，一定要考虑问题的整体，不然就回出现数据不一致的错误，很经典的错误就是脏度（dirtyread）
 * 示例总结：
 *    在我们对一个对象的方法加锁的时候，需要考虑业务的整体性，即为setValue/getValue 方法同时加锁synchronized同步关键字，保证业务（service）的原子性，不然会出现业务错误（也从侧面保证业务的一性）。
 */
public class DirtyRead {
	
	private String username =  "gf";
	
	private String password = "123";
	
	public synchronized void setValue(String username , String password){
		this.username = username;
		
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.password = password;
		
		System.out.println("setValue最终结果：username = " + this.username + " , password =" + this.password);
		
	}
	
	/**
	 * 在此方法上加上synchronized 关键字就可以解决数据 脏读 的情况
	 */
	public void getValue(){
		System.out.println("getValue方法得到：username = " + this.username + " , password = " + this.password);
	}
	
	public static void main(String[] args) throws InterruptedException{
		final DirtyRead dr = new DirtyRead();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				dr.setValue("z3", "456");
			}
		}).start();
		
		Thread.sleep(1000);
		
		dr.getValue();
	}
	
}
