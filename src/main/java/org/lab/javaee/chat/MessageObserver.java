package org.lab.javaee.chat;

import javax.enterprise.event.Observes;

/**
 * Bean used to manage special messages
 *
 * @author Antoine Sabot-Durand
 */
public class MessageObserver {

    public void observesAdWords(@Observes @AdWord String word) {
        System.out.println("Ad word trapped  : " + word);
    }

    public void observesbadWords(@Observes @BadWord String word) {
        System.out.println("Bad word trapped  : " + word);
    }
}
