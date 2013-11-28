package org.lab.javaee.chat;

import javax.websocket.Session;

/**
 * @author Antoine Sabot-Durand
 */
public interface ChatService {
    boolean add(Session session);

    boolean remove(Object o);

    void persistMessage(Message msg);

    void processMessage(String message);
}
