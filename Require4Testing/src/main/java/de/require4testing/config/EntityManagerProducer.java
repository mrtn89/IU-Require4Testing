package de.require4testing.config;

import java.io.Serializable;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // Zentrale Factory für EntityManager-Instanzen
    private volatile EntityManagerFactory entityManagerFactory;

 // Lazy Initialisierung der Factory
    private EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            synchronized (this) {
                if (entityManagerFactory == null) {
                    entityManagerFactory = Persistence.createEntityManagerFactory("require4testingPU");
                }
            }
        }
        return entityManagerFactory;
    }
    
 // Pro Request einen eigenen EntityManager bereitstellen
    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
 
    // EntityManager nach Verwendung schließen
    public void closeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    // Factory beim Herunterfahren der Anwendung freigeben
    @PreDestroy
    public void destroy() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
