package org.lab.javaee.chat;

import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


/**
 * @author Antoine Sabot-Durand
 */
@ServerEndpoint("/websocket")
public class ChatEndpoint {

    @Inject
    private ChatService service;

    @OnOpen
    public void onOpen(Session peer) {
        service.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        service.remove(peer);
    }

    @OnMessage
    public void message(String message, Session client) throws IOException, EncodeException {
        service.processMessage(message);
    }
}
