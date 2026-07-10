package de.require4testing.view;

import de.require4testing.model.Requirement;
import de.require4testing.model.TestCase;
import de.require4testing.model.TestCaseStatus;
import de.require4testing.service.RequirementService;
import de.require4testing.service.TestCaseService;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * View-scoped Bean für die Testfall-Seite.
 * Verwaltet das Anlegen, Speichern und Auflisten von Testfällen inkl. Requirements-Zuordnung.
 */
@Named
@ViewScoped
public class TestCasePageBean extends MessageSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TestCaseService testCaseService;

    @Inject
    private RequirementService requirementService;

    private TestCase testCase = new TestCase();
    private Long requirementId;

    /**
     * Speichert den Testfall mit verknüpftem Requirement.
     * Zeigt Fehler, wenn kein Requirement ausgewählt ist; resetet danach das Formular.
     */
    public void save() {
        if (requirementId == null) {
            error("Bitte eine Anforderung auswählen.");
            return;
        }
        testCaseService.create(testCase, requirementId);
        info("Testfall wurde gespeichert.");
        testCase = new TestCase();
        requirementId = null;
    }

    //Gibt das aktuelle Testfall-Objekt für das Formular zurück. 
    public TestCase getTestCase() {
        return testCase;
    }
    //Setzt das aktuelle Testfall-Objekt.
    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }
    //Gibt die ID des zugeordneten Requirements zurück.
    public Long getRequirementId() {
        return requirementId;
    }

    //Setzt die Requirement-ID aus der Dropdown-Auswahl.
    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }

    //Liefert alle Testfälle für die Übersichtstabelle.
    public List<TestCase> getTestCases() {
        return testCaseService.findAll();
    }
    //Liefert alle Requirements für das SelectOneMenu.
    public List<Requirement> getRequirements() {
        return requirementService.findAll();
    }
    
    //Liefert alle Testfall-Status für das SelectOneMenu.
    public TestCaseStatus[] getStatuses() {
        return TestCaseStatus.values();
    }
}
