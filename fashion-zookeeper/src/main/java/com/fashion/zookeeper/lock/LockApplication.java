package com.fashion.zookeeper.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class LockApplication {

	public static void main(String[] args) {

		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);

		for (int i = 0; i < 10; i++) {
			newFixedThreadPool.submit(createRunable());
		}
	}

	public static Runnable createRunable() {

		return () -> {
			RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

			CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);

			client.start();

			InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");

			acquire(mutex);

			try {
				System.out.println("处理中");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				mutex.release();
			} catch (Exception e) {
				e.printStackTrace();
			}

		};

	}

	private static void acquire(InterProcessMutex mutex) {
		while (true) {
			try {
				mutex.acquire();
			} catch (Exception e) {
				System.out.println("acquire 出错");
				e.printStackTrace();
			}
		}
	}

}
