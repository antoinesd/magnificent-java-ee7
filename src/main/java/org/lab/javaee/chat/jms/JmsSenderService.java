/**
 * Copyright © 2013, 2013, Oracle and/or its affiliates. All rights reserved. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
