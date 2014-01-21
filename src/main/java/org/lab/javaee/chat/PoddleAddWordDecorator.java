package org.lab.javaee.chat;

import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antoine Sabot-Durand
 */
@Decorator
@Priority(Interceptor.Priority.APPLICATION)
public abstract class PoddleAddWordDecorator implements ChatService {


    @Inject
    @Delegate
    private ChatService delegateService;


    private final List<String> adWords = new ArrayList<String>() {{
        add("world");
        add("duck");
        add("cartman");
    }};


    @Inject
    private Event<String> events;


    @Override
    public void processMessage(String message) {
        String lmessage = message.toLowerCase();
        for (String s : adWords) {
            if (lmessage.indexOf(s) > -1) {
                events.fire(s);
            }
        }
        delegateService.processMessage(message);
    }
}
