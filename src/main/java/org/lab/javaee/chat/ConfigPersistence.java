package org.lab.javaee.chat;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Antoine Sabot-Durand
 */


public class ConfigPersistence {

    @PersistenceContext
    @Produces
    private EntityManager em;
}
