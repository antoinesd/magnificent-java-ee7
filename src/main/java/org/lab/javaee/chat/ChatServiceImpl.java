package org.lab.javaee.chat;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;
import javax.persistence.EntityManager;
import javax.websocket.Session;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Antoine Sabot-Durand
 */
@ApplicationScoped
@Log
public class ChatServiceImpl implements ChatService {


    private final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @Inject
    EntityManager em;

    @Override
    public boolean add(Session session) {
        return peers.add(session);
    }

    @Override
    public boolean remove(Object o) {
        return peers.remove(o);
    }

    @Override
    public void persistMessage(Message msg) {
        em.persist(msg);
    }


    @Override
    public void processMessage(String message) {
        System.out.println(message);
        JsonReader reader = Json.createReader(new StringReader(message));
        try {
            JsonObject msgObj = reader.readObject();
            Message msg = new Message();
            msg.setUser(msgObj.getString("user"));
            msg.setContent(msgObj.getString("content"));
            System.out.println("Message from " + msg.getUser() + " : " + msg.getContent());
        } catch (JsonParsingException e) {
            System.out.println("Message is not in JSON format");
        } finally {
            reader.close();
        }
        for (Session peer : peers) {
            peer.getAsyncRemote().sendText(message);
        }
    }
}
