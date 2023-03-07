package com.example.spring.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;

@Component
public class MockServerPush {

    @Autowired
    private MyHandler myHandler;

    @PostConstruct
    public void init() {
        Thread t = new Thread(() -> {
            while (true) {
                for (WebSocketSession session : myHandler.getAllSessions()) {
                    String sessionId = session.getId();
                    try {
                        session.sendMessage(new TextMessage(sessionId + " current time is = " + System.currentTimeMillis()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        //如果发送消息失败，将socket session从数据中移除
                        myHandler.removeSessionById(sessionId);
                    }
                }
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

}
