package de.require4testing.view;

import de.require4testing.model.Requirement;
import de.require4testing.model.TestCase;
import de.require4testing.model.TestExecution;
import de.require4testing.service.RequirementService;
import de.require4testing.service.TestCaseService;
import de.require4testing.service.TestExecutionService;
import de.require4testing.service.TestRunService;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * View-scoped Bean für die Dashboard-Übersichtsseite.
 * Liefert Statistik-Zahlen und Listen für Requirements, Testfälle und Ausführungen.
 */
@Named
@ViewScoped
public class OverviewBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private RequirementService requirementService;

    @Inject
    private TestCaseService testCaseService;

    @Inject
    private TestRunService testRunService;

    @Inject
    private TestExecutionService executionService;

    //Anzahl aller Requirements.
    public long getRequirementCount() {
        return requirementService.countAll();
    }

    //Anzahl aller Testfälle.
    public long getTestCaseCount() {
        return testCaseService.countAll();
    }

    //Anzahl aller Testläufe.
    public long getTestRunCount() {
        return testRunService.countAll();
    }

    //Anzahl bestandener Testausführungen.
    public long getPassedCount() {
        return executionService.countPassed();
    }

    //Anzahl fehlgeschlagener Testausführungen.
    public long getFailedCount() {
        return executionService.countFailed();
    }

    //Liefert alle Requirements.
    public List<Requirement> getRequirements() {
        return requirementService.findAll();
    }
    
    //Liefert alle Testfälle.
    public List<TestCase> getTestCases() {
        return testCaseService.findAll();
    }

    //Liefert alle Testausführungen.
    public List<TestExecution> getExecutions() {
        return executionService.findAll();
    }
}
