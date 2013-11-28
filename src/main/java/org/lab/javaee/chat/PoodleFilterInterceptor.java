package org.lab.javaee.chat;


import org.lab.javaee.chat.jms.JmsSenderService;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antoine Sabot-Durand
 */
@Interceptor
@PoodleFilter
@Priority(Interceptor.Priority.APPLICATION)
public class PoodleFilterInterceptor {

    static final List<String> DICTIONARY = new ArrayList<String>() {{
        add("world");
        add("duck");
        add("cartman");
    }};


    @Inject
    JmsSenderService jms;

    @AroundInvoke
    public Object manageTransaction(InvocationContext ctx) throws Exception {
        if (ctx.getParameters()[0] instanceof String) {
            String message = (String) ctx.getParameters()[0];
            String lmessage = message.toLowerCase();
            for (String s : getDictionary()) {
                if (lmessage.indexOf(s) > -1) {
                    jms.sendMessage(s);
                }
            }
        }
        return ctx.proceed();
    }

    private List<String> getDictionary() {
        return DICTIONARY;
    }

}
