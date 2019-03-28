package cn.me.other;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
1、在方法run上加synchronized,这样这个方法在同一时间只能被一个线程访问,
由于票卖完前while不退出,会导致只有一个窗口在卖票
2、使用lock可以让多个窗口同时卖票且线程安全,需要注意锁生效的区域
3、runError方法有误,原因是当票略大于0时多个线程同时进入循环,
它们已经会一次卖票,导致线程不安全
4、锁必须用同一个,因为进入同一个把锁的线程只有1个,如果写在run里,会出现多把锁,
那么这些锁里的线程可以同时修改同一个变量(j),依旧线程不安全
5、可重入读写锁（读写锁的一个实现，未测试）　
　ReentrantReadWriteLock lock = new ReentrantReadWriteLock()
　　ReadLock r = lock.readLock();
　　WriteLock w = lock.writeLock();
两者都有lock,unlock方法。写写，写读互斥；读读不互斥。可以实现并发读的高效线程安全代码
*/
public class A implements Runnable {
	public static int j = 100;

	public static void main(String[] args) {
		Runnable a = new A();
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(a);
			t.start();

		}
		System.out.println("A.j=" + A.j);
	}

	Lock l = new ReentrantLock();

	public void run() {
		while (true) {
			l.lock();
			try {
				if (j > 0) {
					Thread.sleep(1);
					System.out.println(Thread.currentThread().getName() + "\t" + (--j));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				l.unlock();
			}
		}
	}
	
	public void runError() {
		while (j > 0) {
			l.lock();
			try {
				Thread.sleep(1);
				System.out.println(Thread.currentThread().getName() + "\t" + (--j));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				l.unlock();
			}
		}
	}
}

==========================================
死锁演示：

package cn.me.other;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AA2 implements Runnable {
	private List a = new ArrayList();
	private List b = new ArrayList();
	public static boolean bo;

	public static void main(String[] args) throws Exception {
		AA2 a2 = new AA2();
		for (int i = 0; i < 5; i++) {
			bo = !bo;
			new Thread(a2).start();
		}
	}

	public void run() {
		if (bo) {
			synchronized (a) {
				System.out.println(Thread.currentThread().getName() + "lock a");
				try {
					Thread.sleep(44);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (b) {
					System.out.println(lock b);
				}
			}
		} else {
			synchronized (b) {
				System.out.println(Thread.currentThread().getName() + "lock b");
				try {
					Thread.sleep(44);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (a) {
					System.out.println(lock a);
				}
			}
		}
	}
}
