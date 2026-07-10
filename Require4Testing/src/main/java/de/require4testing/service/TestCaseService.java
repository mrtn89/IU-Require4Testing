package de.require4testing.service;

import de.require4testing.model.Requirement;
import de.require4testing.model.TestCase;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class TestCaseService implements Serializable {

    private static final long serialVersionUID = 1L;

    // Zugriff auf den Persistenzkontext
    @Inject
    private EntityManager entityManager;

    // Speichert einen neuen Testfall zur Anforderung
    public void create(TestCase testCase, Long requirementId) {
        Requirement requirement = entityManager.find(Requirement.class, requirementId);
        testCase.setRequirement(requirement);
        entityManager.getTransaction().begin();
        entityManager.persist(testCase);
        entityManager.getTransaction().commit();
    }
    
    // Sucht einen Testfall anhand der ID
    public TestCase find(Long id) {
        return entityManager.find(TestCase.class, id);
    }

    // Liefert alle Testfälle absteigend nach ID
    public List<TestCase> findAll() {
        TypedQuery<TestCase> query = entityManager.createQuery(
                "select tc from TestCase tc order by tc.id desc", TestCase.class);
        return query.getResultList();
    }
    
    // Zählt alle vorhandenen Testfälle
    public long countAll() {
        return entityManager.createQuery("select count(tc) from TestCase tc", Long.class).getSingleResult();
    }
}
