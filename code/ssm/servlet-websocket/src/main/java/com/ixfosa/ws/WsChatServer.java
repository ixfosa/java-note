package com.ixfosa.ws;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by ixfosa on 2021/4/26 20:08
 */

@ServerEndpoint("/chat")
public class WsChatServer {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("OnOpen...");
    }


    @OnClose
    public void onClose() {
        System.out.println("OnClose...");
    }


    @OnMessage
    public void onMessage(String message) {
        System.out.println("OnMessage..." + message);
    }




    @OnError
    public void onError(Throwable t) throws Throwable {
        System.out.println("OnError...");
    }
}
