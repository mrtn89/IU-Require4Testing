package de.require4testing.service;

import de.require4testing.model.TestExecution;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class TestExecutionService implements Serializable {

    private static final long serialVersionUID = 1L;

    // Zugriff auf den Persistenzkontext
    @Inject
    private EntityManager entityManager;

    // Liefert alle Testausführungen absteigend nach ID
    public List<TestExecution> findAll() {
        TypedQuery<TestExecution> query = entityManager.createQuery(
                "select te from TestExecution te order by te.id desc", TestExecution.class);
        return query.getResultList();
    }

    // Liefert alle Testausführungen eines Testers
    public List<TestExecution> findByTester(Long testerId) {
        TypedQuery<TestExecution> query = entityManager.createQuery(
                "select te from TestExecution te where te.tester.id = :testerId order by te.id desc",
                TestExecution.class);
        query.setParameter("testerId", testerId);
        return query.getResultList();
    }

    // Aktualisiert Ergebnis, Kommentar und Zeitpunkt
    public void update(TestExecution changedExecution) {
        TestExecution managedExecution = entityManager.find(TestExecution.class, changedExecution.getId());
        entityManager.getTransaction().begin();
        managedExecution.setResultStatus(changedExecution.getResultStatus());
        managedExecution.setComment(changedExecution.getComment());
        managedExecution.setExecutedAt(new Date());
        entityManager.getTransaction().commit();
    }

    // Zählt bestandene Testausführungen
    public long countPassed() {
        return entityManager.createQuery(
                "select count(te) from TestExecution te where te.resultStatus = de.require4testing.model.ExecutionResult.BESTANDEN",
                Long.class).getSingleResult();
    }

    // Zählt fehlgeschlagene Testausführungen
    public long countFailed() {
        return entityManager.createQuery(
                "select count(te) from TestExecution te where te.resultStatus = de.require4testing.model.ExecutionResult.FEHLGESCHLAGEN",
                Long.class).getSingleResult();
    }
}
