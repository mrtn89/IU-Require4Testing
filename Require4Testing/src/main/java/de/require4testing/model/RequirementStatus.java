package de.require4testing.model;

//Statuswerte für Anforderungen
public enum RequirementStatus {
    OFFEN("Offen"),
    IN_BEARBEITUNG("In Bearbeitung"),
    ABGENOMMEN("Abgenommen");

	// Anzeigetext des Status
    private final String label;

    RequirementStatus(String label) {
        this.label = label;
    }
    // Liefert den Anzeigetext
    public String getLabel() {
        return label;
    }
}
