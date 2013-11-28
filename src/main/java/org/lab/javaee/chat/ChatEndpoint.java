package org.lab.javaee.chat;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Antoine Sabot-Durand
 */
@ServerEndpoint("/websocket")
public class ChatEndpoint {
    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session peer) {
        peers.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }

    @OnMessage
    @PoodleFilter
    @MappleFilter
    public void message(String message, Session client) {
        for (Session peer : peers) {
            peer.getAsyncRemote().sendText(message);
        }
    }
}
