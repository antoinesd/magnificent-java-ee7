package org.lab.javaee.chat;

import javax.enterprise.event.Observes;

/**
 * Bean used to manage special messages
 *
 * @author Antoine Sabot-Durand
 */
public class MessageObserver {

    public void observesWorldMessages(@Observes String msg) {
        System.out.println("Keyword was trap here : " + msg);
    }
}
