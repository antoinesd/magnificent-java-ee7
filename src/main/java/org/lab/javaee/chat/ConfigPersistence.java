package org.lab.javaee.chat;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Antoine Sabot-Durand
 */


@DataSourceDefinition(
        className = "org.apache.derby.jdbc.EmbeddedDataSource",
        name = "java:global/jdbc/chat",
        user = "app",
        password = "app",
        databaseName = "chat",
        properties = {"connectionAttributes=;create=true"}
)

public class ConfigPersistence {

    @PersistenceContext
    @Produces
    private EntityManager em;
}
