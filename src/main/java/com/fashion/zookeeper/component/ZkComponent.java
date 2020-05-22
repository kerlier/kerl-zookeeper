package com.fashion.zookeeper.component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.fashion.zookeeper.util.ReflectUtil;

public abstract class ZkComponent implements IComponent {

	private ZooKeeper zooKeeper;

	public ZkComponent() throws IOException {
		zooKeeper = new ZooKeeper("localhost:2181", 100, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				if (EventType.NodeDataChanged == event.getType()) {
					System.out.println("success change znode: " + event.getPath());
					reload();
					System.out.println("发生变化");
				}
				if (EventType.None == event.getType() && null == event.getPath()) {
					System.out.println("Zookeeper session established");
				}
			}
		});
		reload();
	}

	public ZkComponent(String zkHosts) throws IOException {
		zooKeeper = new ZooKeeper(zkHosts, 100, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				if (EventType.NodeDataChanged == event.getType()) {
					System.out.println("success change znode: " + event.getPath());
					reload();
					System.out.println("发生变化");
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
		Class<? extends ZkComponent> class1 = this.getClass();
		try {
			Field[] declaredFields = class1.getDeclaredFields();
			for (Field field : declaredFields) {
				String value = new String(zooKeeper.getData("/" + field.getName(), true, stat),
						Charset.forName("utf-8"));
				ReflectUtil.setValue(this, class1, field.getName(),
						this.getClass().getDeclaredField(field.getName()).getType(), value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
