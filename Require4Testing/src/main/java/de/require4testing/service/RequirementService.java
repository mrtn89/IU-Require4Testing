package de.require4testing.service;

import de.require4testing.model.Requirement;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class RequirementService implements Serializable {

    private static final long serialVersionUID = 1L;

    // Zugriff auf den Persistenzkontext
    @Inject
    private EntityManager entityManager;

    // Speichert eine neue Anforderung
    public void create(Requirement requirement) {
        entityManager.getTransaction().begin();
        entityManager.persist(requirement);
        entityManager.getTransaction().commit();
    }
    
    // Sucht eine Anforderung anhand der ID
    public Requirement find(Long id) {
        return entityManager.find(Requirement.class, id);
    }
    
    // Liefert alle Anforderungen absteigend nach ID
    public List<Requirement> findAll() {
        TypedQuery<Requirement> query = entityManager.createQuery(
                "select r from Requirement r order by r.id desc", Requirement.class);
        return query.getResultList();
    }
    // Zählt alle vorhandenen Anforderungen
    public long countAll() {
        return entityManager.createQuery("select count(r) from Requirement r", Long.class).getSingleResult();
    }
}
