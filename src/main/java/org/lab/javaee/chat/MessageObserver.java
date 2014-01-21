package org.lab.javaee.chat;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

/**
 * @author Antoine Sabot-Durand
 */
@ApplicationScoped
public class MessageObserver {

    public void observesWorldMessages(@Observes String msg) {
        System.out.println("Keyword was trap here : " + msg);
    }


}
