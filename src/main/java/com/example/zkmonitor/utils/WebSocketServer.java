package com.example.zkmonitor.utils;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/websocket/zk/{path}")
@Component
public class WebSocketServer {
    private static AtomicInteger onlineNum = new AtomicInteger();
    private String HttpSessionId;
    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<Session, String> map = new ConcurrentHashMap<>();

    //发送消息
    public void sendMessage(Session session, String message) {
        if (session != null) {
            synchronized (session) {
                System.out.println("发送数据：" + message + ",sessionId=" + session.getId());
                try {
                    session.getBasicRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("path") String path) {
        String pathUrl = new String(Base64.getUrlDecoder().decode(path), Charset.forName("utf-8"));
        onlineNum.incrementAndGet();
        sendMessage(session, "%sid=" + session.getId());
        map.put(session, pathUrl);
        System.out.println("websocket链接建立,sessionId=" + session.getId() + "path=" + path + ",map=" + map);
    }

    @OnClose
    public void onClose(Session session, @PathParam("path") String path) {
        path = new String(Base64.getUrlDecoder().decode(path), Charset.forName("utf-8"));
        map.remove(session, path);
        onlineNum.decrementAndGet();
        System.out.println("websocket链接关闭,sessionId=" + session.getId() + ",map=" + map);
    }

    @OnError
    public void onError(Session session, Throwable e) {
        System.out.println("sessionId=" + session.getId() + "websocket服务器出错:" + e.getMessage());
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        if (map.containsKey(session)) {
            sendMessage(session, message);
        } else {
            System.out.println("websocket服务器出错: 无法识别sessionId:" + session.getId());
        }
    }

    public void broadcast(String message) {
        for (Session session : map.keySet()) {
            sendMessage(session, message);
        }
    }

    public void sendMessageByPath(String message, String path) {
        for (Session session : map.keySet()) {
            if (map.get(session).equals(path)) {
                sendMessage(session, message);
            }
        }
    }

    public void sendMessageBySessionId(String message, String sid) {
        for (Session session : map.keySet()) {
            if (session.getId().equals(sid)) {
                sendMessage(session, message);
            }
        }
    }


}
