package org.lab.javaee.chat;

/**
 * @author Antoine Sabot-Durand
 */

import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import java.util.HashMap;
import java.util.Map;

@Decorator
@Priority(Interceptor.Priority.APPLICATION + 10)
public abstract class MapplePoliteDecorator implements ChatService {

    static final Map<String, String> DICTIONARY = new HashMap<String, String>() {{
        put("fuck", "duck");
        put("crap", "trap");
        put("idiots", "world");
        put("cartman", "Stan");
    }};


    @Inject
    @Delegate
    private ChatService delegateService;


    @Inject
    @BadWord
    private Event<String> events;


    @Override
    public void processMessage(String message) {
        String lmessage = message.toLowerCase();
        String res = message;
        for (String word : DICTIONARY.keySet())
            if (lmessage.indexOf(word) > -1) {
                res = res.replaceAll("(?i)" + word, DICTIONARY.get(word));
                events.fire(word);
            }
        delegateService.processMessage(res);
    }
}

