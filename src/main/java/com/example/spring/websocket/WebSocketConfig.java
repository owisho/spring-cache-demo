package com.example.spring.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * websocket server与client还可以协商子协议，例如：STOMP（Simple Text Oriented Message Protocol）
 * 参考地址：https://docs.spring.io/spring-framework/docs/6.0.6/reference/html/web.html#websocket-stomp
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private MyHandler myHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler, "/myHandler").addInterceptors(new HttpSessionHandshakeInterceptor());
    }
//    使用sockjs方式建立websocket连接，参考地址：https://docs.spring.io/spring-framework/docs/6.0.6/reference/html/web.html#websocket
//    sockjs jsclient https://github.com/sockjs/sockjs-client/
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
//        registry.addHandler(myHandler, "/myHandler").withSockJS();
//    }

}
