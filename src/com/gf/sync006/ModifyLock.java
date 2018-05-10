package com.gf.sync006;

/**
 * 同一个对象属性的修改不会影响锁的情况
 * 示例中 t1 线程执行的过程过程中虽然改变了锁的属性 ，但是还是同一个对象(对象的引用并没有变)
 * 
 * @author huanchu
 *
 */
public class ModifyLock {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public synchronized void changeAttribute(String name, int age) {
		try {
			System.out.println("锁对象 ：" + this.hashCode());
			System.out.println("当前线程 ： " + Thread.currentThread().getName() + "开始");
			this.setName(name);
			this.setAge(age);
			
			System.out.println(
					"当前线程 ： " + Thread.currentThread().getName() + "修改对象内容为：" + this.getName() + ", " + this.getAge());

			Thread.sleep(2000);
			System.out.println("当前线程 ： " + Thread.currentThread().getName() + "结束");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		final ModifyLock modifyLock = new ModifyLock();

		new Thread(new Runnable() {

			@Override
			public void run() {
				modifyLock.changeAttribute("张三", 20);
			}
		}, "t1").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				modifyLock.changeAttribute("李四", 21);
			}
		}, "t2").start();
	}

}
