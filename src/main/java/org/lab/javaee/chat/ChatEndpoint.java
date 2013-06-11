package org.lab.javaee.chat;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;


public interface ChatEndpoint {
    void onOpen(Session peer);

    void onClose(Session peer);

    void message(String message, Session client) throws IOException, EncodeException;
}
