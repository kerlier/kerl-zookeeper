package com.fashion.zookeeper.component;

import java.io.IOException;

import org.junit.Test;

import junit.framework.TestCase;

public class ZkComponentTest extends TestCase {

	@Test
	public void test() throws IOException {
		
		ZkComponent zkComponent = new ZkComponent("localhost:2181");
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
