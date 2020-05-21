package com.fashion.zookeeper.component;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZkComponent implements IComponent {

	private ZooKeeper zooKeeper;

	private String name;
	
	private String name2;

	public ZkComponent(String zkHosts) throws IOException {

		zooKeeper = new ZooKeeper(zkHosts, 100, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				if (EventType.NodeDataChanged == event.getType()) {
					System.out.println("success change znode: " + event.getPath());
					reload();
					System.out.println("发生变化" + name);
				}
				if (EventType.None == event.getType() && null == event.getPath()) {
					System.out.println("Zookeeper session established");
				}
			}
		});
		reload();
	}

	public void reload() {
		Stat stat = new Stat();
		try {
			String value = new String(zooKeeper.getData("/data", true, stat), Charset.forName("utf-8"));
			String value1 = new String(zooKeeper.getData("/data1", true, stat), Charset.forName("utf-8"));
			this.name = value;
			this.name2 = value1;
			System.out.println("当前值1" + this.name);
			System.out.println("当前值2" + this.name2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
