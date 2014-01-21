package org.lab.javaee.chat;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Antoine Sabot-Durand
 */
@ApplicationScoped
public class ChatService {

    private final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    public boolean add(Session session) {
        return peers.add(session);
    }

    public boolean remove(Object o) {
        return peers.remove(o);
    }


    public void processMessage(String message) {
        for (Session peer : peers) {
            peer.getAsyncRemote().sendText(message);
        }
    }
}
