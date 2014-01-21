package org.lab.javaee.chat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * @author Antoine Sabot-Durand
 */

@Transactional
public class MessageService {

    @Inject
    EntityManager em;


    public void persistMessage(Message msg) {
        em.persist(msg);
    }


}
