package org.lab.javaee.chat;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * @author Antoine Sabot-Durand
 */
@Decorator
public abstract class PoddleAddWordDecorator implements ChatService {


    @Inject
    @Delegate
    private ChatService delegateService;


    @Inject
    private Event<String> events;


    @Override
    public void processMessage(String message) {
        if (message.toLowerCase().indexOf("world") > -1)
            events.fire(message);
    }
}
