/**
 * Copyright © 2013, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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
