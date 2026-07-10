package de.require4testing.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "testers")
public class Tester implements Serializable {

    private static final long serialVersionUID = 1L;

    // Primärschlüssel des Testers
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Vorname des Testers
    @Column(nullable = false, length = 60)
    private String firstName;

    // Nachname des Testers
    @Column(nullable = false, length = 60)
    private String lastName;

    // Eindeutige E-Mail-Adresse
    @Column(nullable = false, length = 120, unique = true)
    private String email;

    // Ausführungen des Testers
    @OneToMany(mappedBy = "tester", cascade = CascadeType.ALL)
    private List<TestExecution> executions = new ArrayList<TestExecution>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TestExecution> getExecutions() {
        return executions;
    }

    public void setExecutions(List<TestExecution> executions) {
        this.executions = executions;
    }

    // Liefert Vor- und Nachnamen
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
