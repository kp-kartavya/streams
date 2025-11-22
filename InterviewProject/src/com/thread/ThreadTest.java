package com.thread;

public class ThreadTest {

	public static void main(String[] args) {
		// Thread ByExtendingThreadClass
		ByExtendingThreadClass thread1 = new ByExtendingThreadClass();
		thread1.start();
		
		// Thread ByImplementingRunnableInterface
		ByImplementingRunnableInterface runnable = new ByImplementingRunnableInterface();
		Thread thread2 = new Thread(runnable);
		thread2.start();
	}

}
