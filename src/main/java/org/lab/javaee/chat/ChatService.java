package org.lab.javaee.chat;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.websocket.Session;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Antoine Sabot-Durand
 */
@ApplicationScoped
public class ChatService {

    @Inject
    private Event<String> events;

    private final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    public boolean add(Session session) {
        return peers.add(session);
    }

    public boolean remove(Object o) {
        return peers.remove(o);
    }


    public void processMessage(String message) {
        if (message.toLowerCase().indexOf("world") > -1)
            events.fire(message);
        for (Session peer : peers) {
            peer.getAsyncRemote().sendText(message);
        }
    }
}
