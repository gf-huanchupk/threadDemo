package com.gf.sync007;

public class RunThread extends Thread{
	
	private volatile boolean isRunning = true;
	public void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	@Override
	public void run() {
		System.out.println("进入run方法..");
		while (isRunning == true){
			//..
		}
		System.out.println("线程停止");
	}
	
	public static void main(String[] args) throws InterruptedException {
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(3000);
		rt.setRunning(false);
		System.out.println("isRunning的值已经设置了false");
	}

}
