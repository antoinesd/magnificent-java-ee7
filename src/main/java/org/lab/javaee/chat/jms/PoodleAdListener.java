package org.lab.javaee.chat.jms;

import org.lab.javaee.chat.AdWord;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author Antoine Sabot-Durand
 */

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:global/jms/myQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"),
})
public class PoodleAdListener implements MessageListener {

    @Inject
    @AdWord
    private Event<String> adWordEvents;

    @Override
    public void onMessage(Message msg) {
        try {
            System.out.println("**** Poodle MDB receiving Ad Word thru jms : " + msg.getJMSMessageID());
            adWordEvents.fire(msg.getBody(String.class));
        } catch (JMSException e) {
            throw new RuntimeException("Something nasty happened", e);
        }
    }
}
