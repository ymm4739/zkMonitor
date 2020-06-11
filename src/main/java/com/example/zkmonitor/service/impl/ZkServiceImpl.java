package com.example.zkmonitor.service.impl;

import com.example.zkmonitor.config.ZkClient;
import com.example.zkmonitor.domain.ZNode;
import com.example.zkmonitor.service.ZkService;
import com.example.zkmonitor.utils.WebSocketServer;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZkServiceImpl implements ZkService {

    private static ZooKeeper zk = ZkClient.client();

    private Watcher watcher;

    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    public ZNode get(String path) {
        ZNode zNode = new ZNode();
        try {
            zNode.setPath(path);
            Stat stat = new Stat();
            String data = "";
            byte[] bytes = zk.getData(path, false, stat);
            if (stat.getDataLength() > 0) {
                data = new String(bytes, Charset.forName("utf-8"));
            }
            zNode.setData(data);
            zNode.setStat(stat);
            zNode.setName(path.substring(path.lastIndexOf("/") + 1));
            if ("/".equals(path)) {
                zNode.setName("/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zNode;
    }

    @Override
    public List<ZNode> getChildrenInfo(String path) {
        List<ZNode> zNodes = new ArrayList<>();
        try {
            List<String> children = zk.getChildren(path, false);

            for (String child : children) {
                ZNode childNode = new ZNode();
                childNode.setName(child);
                String childPath = path.endsWith("/") ? path + child : path + "/" + child;
                childNode.setPath(childPath);
                Stat stat = new Stat();
                zk.getData(childPath, false, stat);
                childNode.setIsParent(stat.getNumChildren() > 0 ? true : false);
                zNodes.add(childNode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zNodes;
    }

    @Override
    public ZNode dataChange(String path, String sid) {
        ZNode zNode = new ZNode();
        try {
            zNode.setPath(path);
            Stat stat = new Stat();
            String data = "";
            byte[] bytes = zk.getData(path, (event) -> webSocketServer.sendMessageBySessionId("%sid=" + sid, sid), stat);
            if (stat.getDataLength() > 0) {
                data = new String(bytes, Charset.forName("utf-8"));
            }
            zNode.setData(data);
            zNode.setStat(stat);
            zNode.setName(path.substring(path.lastIndexOf("/") + 1));
            if ("/".equals(path)) {
                zNode.setName("/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zNode;
    }
}
