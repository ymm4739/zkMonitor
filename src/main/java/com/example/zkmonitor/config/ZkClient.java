package com.example.zkmonitor.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Component;

@Component
public class ZkClient {
    private static String connectionStr = "127.0.0.1:2181";
    private static int sessionTimeout = 10;
    private static boolean readOnly = true;

    public static ZooKeeper client() {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(connectionStr, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println(watchedEvent.getType().name());
                }
            }, readOnly);
            return zooKeeper;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
