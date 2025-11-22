package com.thread;

public class ByExtendingThreadClass extends Thread {
	@Override
	public void run() {
		System.out.println("ByExtendingThreadClass is running...");
	}
}
