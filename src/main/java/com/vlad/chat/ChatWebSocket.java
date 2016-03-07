package com.vlad.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Владислав on 07.03.2016.
 */
@WebSocket
public class ChatWebSocket {
    private Set<ChatWebSocket> users;
    private Session session;

    public ChatWebSocket(Set<ChatWebSocket> users) {
        this.users = users;
    }

    @OnWebSocketMessage
    public void onMessage(String data){
        for(ChatWebSocket socket: users){
            try {
                socket.getSession().getRemote().sendString(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason){
        users.remove(this);
    }

    @OnWebSocketConnect
    public void onOpen(Session session){
        users.add(this);
        setSession(session);
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
