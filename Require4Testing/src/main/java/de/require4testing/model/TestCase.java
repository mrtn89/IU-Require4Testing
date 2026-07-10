package de.require4testing.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "test_cases")
public class TestCase implements Serializable {

    private static final long serialVersionUID = 1L;

    // Primärschlüssel des Testfalls
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Titel des Testfalls
    @Column(nullable = false, length = 120)
    private String title;

    // Optionale Vorbedingung
    @Column(length = 1200)
    private String precondition;

    // Auszuführende Testschritte
    @Column(nullable = false, length = 2500)
    private String steps;

    // Erwartetes Ergebnis des Tests
    @Column(nullable = false, length = 1500)
    private String expectedResult;

    // Aktueller Bearbeitungsstatus
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TestCaseStatus status = TestCaseStatus.ENTWURF;

    // Zugehörige Anforderung
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "requirement_id")
    private Requirement requirement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrecondition() {
        return precondition;
    }

    public void setPrecondition(String precondition) {
        this.precondition = precondition;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public TestCaseStatus getStatus() {
        return status;
    }

    public void setStatus(TestCaseStatus status) {
        this.status = status;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }
}
