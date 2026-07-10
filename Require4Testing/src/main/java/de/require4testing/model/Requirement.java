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
@Table(name = "requirements")
public class Requirement implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // Primärschlüssel der Anforderung
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kurztitel der Anforderung
    @Column(nullable = false, length = 120)
    private String title;

    // Fachliche Beschreibung der Anforderung
    @Column(nullable = false, length = 2000)
    private String description;

    // Priorität nach fachlicher Einordnung
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RequirementPriority priority = RequirementPriority.MUST;

    // Aktueller Bearbeitungsstatus
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private RequirementStatus status = RequirementStatus.OFFEN;

    // Erstellungszeitpunkt der Anforderung
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt = new Date();

    // Zugehörige Testfälle zur Anforderung
    @OneToMany(mappedBy = "requirement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestCase> testCases = new ArrayList<TestCase>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequirementPriority getPriority() {
        return priority;
    }

    public void setPriority(RequirementPriority priority) {
        this.priority = priority;
    }

    public RequirementStatus getStatus() {
        return status;
    }

    public void setStatus(RequirementStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }
}
