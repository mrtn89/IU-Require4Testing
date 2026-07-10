package de.require4testing.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "test_runs")
public class TestRun implements Serializable {

    private static final long serialVersionUID = 1L;

    // Primärschlüssel des Testlaufs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name des Testlaufs
    @Column(nullable = false, length = 120)
    private String name;

    // Geplantes Datum des Testlaufs
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date plannedDate = new Date();

    // Aktueller Status des Testlaufs
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TestRunStatus status = TestRunStatus.GEPLANT;

    // Zugehörige Testausführungen
    @OneToMany(mappedBy = "testRun", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestExecution> executions = new ArrayList<TestExecution>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public TestRunStatus getStatus() {
        return status;
    }

    public void setStatus(TestRunStatus status) {
        this.status = status;
    }

    public List<TestExecution> getExecutions() {
        return executions;
    }

    public void setExecutions(List<TestExecution> executions) {
        this.executions = executions;
    }
}
