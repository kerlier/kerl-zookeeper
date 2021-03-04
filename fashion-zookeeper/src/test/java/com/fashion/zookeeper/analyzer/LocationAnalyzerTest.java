package com.fashion.zookeeper.analyzer;

import java.io.IOException;

import org.junit.Test;

public class LocationAnalyzerTest {

	@Test
	public void test() throws IOException {
		LocationAnalyzer locationAnalyzer = new LocationAnalyzer("localhost:2181");
		while (true) {
			System.out.println(locationAnalyzer.toString());
			System.out.println(locationAnalyzer.hashCode());
			System.out.println("host:" + locationAnalyzer.getLocationHost());
			System.out.println("port:" + locationAnalyzer.getLocationPort());

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
