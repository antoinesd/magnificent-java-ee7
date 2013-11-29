package org.lab.javaee.chat.jms;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.Queue;

/**
 * @author Antoine Sabot-Durand
 */

@JMSDestinationDefinition(name = "java:global/jms/myQueue",
        resourceAdapter = "jmsra",
        interfaceName = "javax.jms.Queue",
        destinationName = "classicQueue",
        description = "My Sync Queue")


public class JmsSenderService {

    @Resource(mappedName = "java:global/jms/myQueue")
    private Queue myQueue;

    @Inject
    private JMSContext jmsContext;

    public void sendMessage(String message) {
        jmsContext.createProducer().send(myQueue, message);
    }
}
