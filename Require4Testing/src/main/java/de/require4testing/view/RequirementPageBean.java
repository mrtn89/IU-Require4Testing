package de.require4testing.view;

import de.require4testing.model.Requirement;
import de.require4testing.model.RequirementPriority;
import de.require4testing.model.RequirementStatus;
import de.require4testing.service.RequirementService;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * View-scoped Bean für die Anforderungs-Seite (Requirements).
 * Verwaltet das Anlegen, Speichern und Auflisten von Requirements.
 */
@Named
@ViewScoped
public class RequirementPageBean extends MessageSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private RequirementService requirementService;

    private Requirement requirement = new Requirement();

    //Speichert das aktuelle Anforderungen, zeigt Infos an und setzt das Formular zurück.
    public void save() {
        requirementService.create(requirement);
        info("Anforderung wurde gespeichert.");
        requirement = new Requirement();
    }

    //Gibt das aktuelle Anforderungs-Objekt für das Formular zurück.
    public Requirement getRequirement() {
        return requirement;
    }

    //Setzt die aktuelle Anforderung.
    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }

    //Liefert alle Anforderungen für die Übersichtstabelle.
    public List<Requirement> getRequirements() {
        return requirementService.findAll();
    }

    //Liefert alle Prioritäten für das SelectOneMenu.
    public RequirementPriority[] getPriorities() {
        return RequirementPriority.values();
    }

    //Liefert alle Statuswerte für das SelectOneMenu. 
    public RequirementStatus[] getStatuses() {
        return RequirementStatus.values();
    }
}
