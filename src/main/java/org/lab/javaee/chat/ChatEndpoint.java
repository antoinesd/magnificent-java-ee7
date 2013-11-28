package org.lab.javaee.chat;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Antoine Sabot-Durand
 */
@ServerEndpoint("/websocket")
public class ChatEndpoint {
    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());


    @Inject
    private Event<String> events;

    @OnOpen
    public void onOpen(Session peer) {
        peers.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }

    @OnMessage
    public void message(String message, Session client) throws IOException, EncodeException {
        if (message.toLowerCase().indexOf("world") > -1)
            events.fire(message);
        for (Session peer : peers) {
            peer.getAsyncRemote().sendText(message);
        }
    }
}
