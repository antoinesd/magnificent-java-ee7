package org.lab.javaee.chat;


import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Decorator
@Priority(Interceptor.Priority.APPLICATION + 10)
public abstract class ChatEndPoliteDecorator implements ChatEndpoint {

    static final Map<String, String> DICTIONARY = new HashMap<String, String>() {{
        put("fuck", "duck");
        put("crap", "trap");
        put("idiots", "world");
        put("cartman", "Stan");
    }};
    @Inject
    @Delegate
    private ChatEndpoint chatEndpoint;

    @Override
    public void message(String message, Session client) throws IOException, EncodeException {
        String lmessage = message.toLowerCase();
        String res = message;

        for (String word : getDictionary().keySet())
            if (lmessage.indexOf(word) > -1)
                res = res.replaceAll("(?i)" + word, getDictionary().get(word));

        chatEndpoint.message(res, client);
    }

    private Map<String, String> getDictionary() {
        return DICTIONARY;
    }
}
