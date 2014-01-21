package org.lab.javaee.chat;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * @author Antoine Sabot-Durand
 */
@Interceptor
@Log
@Priority(Interceptor.Priority.APPLICATION)
public class LoggerInterceptor {

    @AroundInvoke
    public Object manageTransaction(InvocationContext ctx) throws Exception {
        System.out.println("*** Before " + ctx.getMethod().toString());
        Object res = ctx.proceed();
        System.out.println("*** After " + ctx.getMethod().toString());
        return res;

    }
}
