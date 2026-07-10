package de.require4testing.view;

import de.require4testing.model.TestCase;
import de.require4testing.model.TestExecution;
import de.require4testing.model.TestRun;
import de.require4testing.model.TestRunStatus;
import de.require4testing.model.Tester;
import de.require4testing.service.TestCaseService;
import de.require4testing.service.TestExecutionService;
import de.require4testing.service.TestRunService;
import de.require4testing.service.TesterService;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * View-scoped Bean für die Testlauf-Seite.
 * Verwaltet das Anlegen von Testläufen und die Zuordnung von Testfällen zu Testern.
 */
@Named
@ViewScoped
public class TestRunPageBean extends MessageSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TestRunService testRunService;

    @Inject
    private TestCaseService testCaseService;

    @Inject
    private TesterService testerService;

    @Inject
    private TestExecutionService executionService;

    private TestRun testRun = new TestRun();
    private Long testRunId;
    private Long testCaseId;
    private Long testerId;

  //Speichert einen neuen Testlauf, zeigt Info an und resetet das Formular.
    public void saveRun() {
        testRunService.create(testRun);
        info("Testlauf wurde gespeichert.");
        testRun = new TestRun();
    }
    
    /**
     * Ordnet einen Testfall mit einem Tester einem Testlauf zu.
     * Validiert, dass alle drei IDs gesetzt sind; fängt service-seitige Fehler ab.
     */
    public void assign() {
        if (testRunId == null || testCaseId == null || testerId == null) {
            error("Bitte Testlauf, Testfall und Tester:in auswählen.");
            return;
        }
        try {
            testRunService.assign(testRunId, testCaseId, testerId);
            info("Zuordnung wurde gespeichert.");
            testRunId = null;
            testCaseId = null;
            testerId = null;
        } catch (IllegalArgumentException ex) {
            error(ex.getMessage());
        }
    }
    
    //Gibt das aktuelle Testlauf-Objekt für das Formular zurück.
    public TestRun getTestRun() {
        return testRun;
    }

    //Setzt das aktuelle Testlauf-Objekt
    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }

    //Gibt die ID des ausgewählten Testlaufs zurück.
    public Long getTestRunId() {
        return testRunId;
    }

    //Setzt die Testlauf-ID aus der Dropdown-Auswahl.
    public void setTestRunId(Long testRunId) {
        this.testRunId = testRunId;
    }
    
    //Gibt die ID des ausgewählten Testfalls zurück.
    public Long getTestCaseId() {
        return testCaseId;
    }

    //Setzt die Testfall-ID aus der Dropdown-Auswahl.
    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    //Gibt die ID des ausgewählten Testers zurück.
    public Long getTesterId() {
        return testerId;
    }

    //Setzt die Tester-ID aus der Dropdown-Auswahl.
    public void setTesterId(Long testerId) {
        this.testerId = testerId;
    }

    //Liefert alle Testläufe für die Übersichtstabelle.
    public List<TestRun> getTestRuns() {
        return testRunService.findAll();
    }

    //Liefert alle Testfälle für das SelectOneMenu.
    public List<TestCase> getTestCases() {
        return testCaseService.findAll();
    }

    //Liefert alle Tester für das SelectOneMenu
    public List<Tester> getTesters() {
        return testerService.findAll();
    }

    //Liefert alle Testausführungen für die Übersichtstabelle.
    public List<TestExecution> getAssignments() {
        return executionService.findAll();
    }

    //Liefert alle Testlauf-Status für das SelectOneMenu.
    public TestRunStatus[] getStatuses() {
        return TestRunStatus.values();
    }
}
