package org.lab.javaee.chat;


import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;

@Decorator
@Priority(Interceptor.Priority.APPLICATION)
public abstract class ChatEndListenerDecorator implements ChatEndpoint {

    @Inject
    @Delegate
    private ChatEndpoint chatEndpoint;
    @Inject
    private Event<String> events;

    @Override
    public void message(String message, Session client) throws IOException, EncodeException {
        if (message.toLowerCase().indexOf("world") > -1 || message.toLowerCase().indexOf("cartman") > -1)
            events.fire(message);
        chatEndpoint.message(message, client);
    }
}
