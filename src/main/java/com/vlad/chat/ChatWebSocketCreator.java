package com.vlad.chat;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Владислав on 07.03.2016.
 */
public class ChatWebSocketCreator implements WebSocketCreator {
    private Set<ChatWebSocket> users;

    public ChatWebSocketCreator(){
        this.users = Collections.newSetFromMap(new ConcurrentHashMap<ChatWebSocket, Boolean>());
    }
    @Override
    public Object createWebSocket(ServletUpgradeRequest servletUpgradeRequest, ServletUpgradeResponse servletUpgradeResponse) {
        ChatWebSocket socket = new ChatWebSocket(users);
        return socket;
    }
}
