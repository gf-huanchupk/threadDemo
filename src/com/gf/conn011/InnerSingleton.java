package com.gf.conn011;

public class InnerSingleton {
	
	private static class Singletion{
		private static Singletion singletion = new Singletion();
	}
	
	public static Singletion getInstance(){
		return Singletion.singletion;
	}
	
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(InnerSingleton.getInstance().hashCode());
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(InnerSingleton.getInstance().hashCode());
			}
		}, "t2");

		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(InnerSingleton.getInstance().hashCode());
			}
		}, "t3");
		
		t1.start();
		t2.start();
		t3.start();

	}
	
}
