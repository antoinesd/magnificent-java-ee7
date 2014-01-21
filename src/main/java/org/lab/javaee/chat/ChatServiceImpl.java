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
public class ChatServiceImpl implements ChatService {


    private final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @Override
    public boolean add(Session session) {
        return peers.add(session);
    }

    @Override
    public boolean remove(Object o) {
        return peers.remove(o);
    }


    @Override
    public void processMessage(String message) {
        for (Session peer : peers) {
            peer.getAsyncRemote().sendText(message);
        }
    }
}
