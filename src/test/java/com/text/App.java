package com.text;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * watch test
 *
 */
public class App implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    public static Stat stat = new Stat();
    ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String p = "/testaa";
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, new App());
        connectedSemaphore.await();
        //exists register watch
        zooKeeper.exists(p, true);
        String path = zooKeeper.create(p, "456".getBytes(), Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        //get register watch
        zooKeeper.getData(path, true, stat);
        zooKeeper.setData(path, "hhhh".getBytes(), -1);
        zooKeeper.exists(path, true);
        //exists register watch
        zooKeeper.delete(path, -1); 

    }

    public void process(WatchedEvent event) {
        if (KeeperState.SyncConnected == event.getState()) {
            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
                System.out.println("Zookeeper session established");
            } else if (EventType.NodeCreated == event.getType()) {
                System.out.println("success create znode");

            } else if (EventType.NodeDataChanged == event.getType()) {
                System.out.println("success change znode: " + event.getPath());

            } else if (EventType.NodeDeleted == event.getType()) {
                System.out.println("success delete znode");

            } else if (EventType.NodeChildrenChanged == event.getType()) {
                System.out.println("NodeChildrenChanged");

            }

        }
    }
}