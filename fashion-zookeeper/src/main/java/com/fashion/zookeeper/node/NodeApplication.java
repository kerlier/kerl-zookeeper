package com.fashion.zookeeper.node;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class NodeApplication {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

		ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 2000, null);

		System.out.println(zooKeeper.exists("/tmp/server", false));

//		String create2 = zooKeeper.create("/tmp", "0".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//		System.out.println(create2);
		
		//注意: zookeeper在父节点不存在的情况下，不能直接将创建子节点
		//创建临时节点，当这个程序断开连接后，会将这个节点删除
		String create = zooKeeper.create("/tmp/server", "1".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL_SEQUENTIAL);

		System.out.println(create);

		String create1 = zooKeeper.create("/tmp/server", "2".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL_SEQUENTIAL);

		System.out.println(create1);

		Thread.sleep(Long.MAX_VALUE);
	}

}
