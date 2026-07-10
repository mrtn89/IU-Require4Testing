package de.require4testing.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "test_executions")
public class TestExecution implements Serializable {

    private static final long serialVersionUID = 1L;

    // Primärschlüssel der Testausführung
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Zugehöriger Testlauf
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "test_run_id")
    private TestRun testRun;

    // Ausgeführter Testfall
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "test_case_id")
    private TestCase testCase;

    // Zuständiger Tester
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tester_id")
    private Tester tester;

    // Ergebnisstatus der Ausführung
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ExecutionResult resultStatus = ExecutionResult.OFFEN;

 	// Kommentar zur Ausführung
    @Column(length = 1500)
    private String comment;

    // Zeitpunkt der Ausführung
    @Temporal(TemporalType.TIMESTAMP)
    private Date executedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestRun getTestRun() {
        return testRun;
    }

    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public Tester getTester() {
        return tester;
    }

    public void setTester(Tester tester) {
        this.tester = tester;
    }

    public ExecutionResult getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ExecutionResult resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(Date executedAt) {
        this.executedAt = executedAt;
    }
}
