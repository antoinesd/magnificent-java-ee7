package org.lab.javaee.chat;

import javax.enterprise.event.Observes;

/**
 * @author Antoine Sabot-Durand
 */
public class MessageObserver {

    public void observesAdWords(@Observes @AdWord String msg) {
        System.out.println("AdWord was trap here : " + msg);
    }

    public void observesBadWords(@Observes @BadWord String msg) {
        System.out.println("Bad word was trap here : " + msg);
    }


}
