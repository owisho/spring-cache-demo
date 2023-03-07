package com.example.spring.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

@Component
public class MyHandler extends TextWebSocketHandler {

    private Map<String, WebSocketSession> sessionMap = new HashMap<>();

    public Collection<WebSocketSession> getAllSessions() {
        return sessionMap.values();
    }

    public void removeSessionById(String id) {
        sessionMap.remove(id);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionMap.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(String.format("user=%s,message=%s", session.getId(), message.toString()));
        super.handleTextMessage(session, message);
    }

}
