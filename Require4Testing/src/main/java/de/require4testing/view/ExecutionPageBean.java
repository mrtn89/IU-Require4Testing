package de.require4testing.view;

import de.require4testing.model.ExecutionResult;
import de.require4testing.model.TestExecution;
import de.require4testing.model.Tester;
import de.require4testing.service.TestExecutionService;
import de.require4testing.service.TesterService;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 * View-scoped Bean für die Testausführungs-Seite.
 * Verwaltet Testausführungen, Tester-Auswahl und Ergebnisanzeige.
 */
@Named
@ViewScoped
public class ExecutionPageBean extends MessageSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TestExecutionService executionService;

    @Inject
    private TesterService testerService;

    private Long testerId;

    //Speichert eine Testausführung und gibt eine Rückmeldung aus.
    public void save(TestExecution execution) {
        executionService.update(execution);
        info("Testergebnis wurde gespeichert.");
    }

    //Gibt die ID des aktuell ausgewählten Testers zurück.
    public Long getTesterId() {
        return testerId;
    }
    //Setzt die ausgewählte Tester-ID aus dem Dropdown-Menü.
    public void setTesterId(Long testerId) {
        this.testerId = testerId;
    }
    //Liefert alle Tester für die Dropdown-Auswahl.
    public List<Tester> getTesters() {
        return testerService.findAll();
    }

    //Liefert Testausführungen, ggf. gefiltert nach ausgewähltem Tester.
    public List<TestExecution> getExecutions() {
        if (testerId == null) {
            return executionService.findAll();
        }
        return executionService.findByTester(testerId);
    }

    //Liefert alle möglichen Ausführungsergebnisse (Enum-Werte).
    public ExecutionResult[] getResults() {
        return ExecutionResult.values();
    }
    
    //Mappt ein ExecutionResult auf eine CSS-Klasse für die Farbliche Darstellung.
    public String getResultCss(ExecutionResult result) {
        if (result == null) {
            return "result-offen";
        }
        switch (result) {
            case BESTANDEN:
                return "result-bestanden";
            case FEHLGESCHLAGEN:
                return "result-fehlgeschlagen";
            default:
                return "result-offen";
        }
    }
}
