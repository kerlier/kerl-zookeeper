package com.fashion.zookeeper.analyzer;

import java.io.IOException;

import org.junit.Test;

public class LocationAnalyzerTest {

	@Test
	public void test() throws IOException {
		LocationAnalyzer locationAnalyzer = new LocationAnalyzer("localhost:2181");
		while (true) {
			System.out.println(locationAnalyzer.toString());

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
