package com.fashion.zookeeper.analyzer;

import java.io.IOException;

import org.junit.Test;

public class LocationAnalyzerTest {

	@Test
	public void test() throws IOException {
		LocationAnalyzer locationAnalyzer = new LocationAnalyzer("localhost:2181");
//		BoxAnalyzer boxAnalyzer = new BoxAnalyzer();
		while (true) {
			System.out.println(locationAnalyzer.toString());
//			System.out.println(boxAnalyzer.toString());

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
