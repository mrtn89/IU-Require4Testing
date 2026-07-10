package de.require4testing.service;

import de.require4testing.model.ExecutionResult;
import de.require4testing.model.TestCase;
import de.require4testing.model.TestExecution;
import de.require4testing.model.TestRun;
import de.require4testing.model.Tester;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class TestRunService implements Serializable {

    private static final long serialVersionUID = 1L;

    // Zugriff auf den Persistenzkontext
    @Inject
    private EntityManager entityManager;

    // Speichert einen neuen Testlauf
    public void create(TestRun testRun) {
        entityManager.getTransaction().begin();
        entityManager.persist(testRun);
        entityManager.getTransaction().commit();
    }

    // Ordnet einen Testfall einem Tester im Testlauf zu
    public void assign(Long testRunId, Long testCaseId, Long testerId) {
        TypedQuery<Long> duplicateQuery = entityManager.createQuery(
                "select count(te) from TestExecution te where te.testRun.id = :runId and te.testCase.id = :caseId and te.tester.id = :testerId",
                Long.class);
        duplicateQuery.setParameter("runId", testRunId);
        duplicateQuery.setParameter("caseId", testCaseId);
        duplicateQuery.setParameter("testerId", testerId);
        Long count = duplicateQuery.getSingleResult();
        
        // Verhindert doppelte Zuordnungen
        if (count != null && count.longValue() > 0L) {
            throw new IllegalArgumentException("Diese Zuordnung existiert bereits.");
        }

        // Lädt die beteiligten Entitäten
        TestRun testRun = entityManager.find(TestRun.class, testRunId);
        TestCase testCase = entityManager.find(TestCase.class, testCaseId);
        Tester tester = entityManager.find(Tester.class, testerId);

        // Erstellt eine neue Testausführung
        TestExecution testExecution = new TestExecution();
        testExecution.setTestRun(testRun);
        testExecution.setTestCase(testCase);
        testExecution.setTester(tester);
        testExecution.setResultStatus(ExecutionResult.OFFEN);

        entityManager.getTransaction().begin();
        entityManager.persist(testExecution);
        entityManager.getTransaction().commit();
    }

    // Liefert alle Testläufe absteigend nach ID
    public List<TestRun> findAll() {
        TypedQuery<TestRun> query = entityManager.createQuery(
                "select tr from TestRun tr order by tr.id desc", TestRun.class);
        return query.getResultList();
    }
    
    // Zählt alle vorhandenen Testläufe
    public long countAll() {
        return entityManager.createQuery("select count(tr) from TestRun tr", Long.class).getSingleResult();
    }
}
