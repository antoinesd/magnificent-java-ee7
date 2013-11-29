package org.lab.javaee.chat;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.StringReader;
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
