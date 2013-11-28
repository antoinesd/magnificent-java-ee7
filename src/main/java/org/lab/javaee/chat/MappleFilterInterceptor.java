package org.lab.javaee.chat;


import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.HashMap;
import java.util.Map;

@Interceptor
@MappleFilter
@Priority(Interceptor.Priority.APPLICATION + 10)
public class MappleFilterInterceptor {

    static final Map<String, String> DICTIONARY = new HashMap<String, String>() {{
        put("fuck", "duck");
        put("crap", "trap");
        put("idiots", "world");
        put("cartman", "Stan");
    }};

    @Inject
    @BadWord
    private Event<String> badWordEvents;

    @AroundInvoke
    public Object manageTransaction(InvocationContext ctx) throws Exception {

        if (ctx.getParameters()[0] instanceof String) {
            String message = (String) ctx.getParameters()[0];
            String lmessage = message.toLowerCase();
            String res = message;
            for (String word : getDictionary().keySet())
                if (lmessage.indexOf(word) > -1) {
                    res = res.replaceAll("(?i)" + word, getDictionary().get(word));
                    Object[] params = ctx.getParameters();
                    params[0] = res;
                    ctx.setParameters(params);
                    badWordEvents.fire(word);
                }
        }
        return ctx.proceed();
    }


    private Map<String, String> getDictionary() {
        return DICTIONARY;
    }
}
