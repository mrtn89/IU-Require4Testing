package de.require4testing.model;

//Prioritätsstufen für Anforderungen
public enum RequirementPriority {
    MUST("MUST"),
    SHOULD("SHOULD"),
    COULD("COULD");

	// Anzeigetext der Priorität
    private final String label;

    RequirementPriority(String label) {
        this.label = label;
    }

    // Liefert den Anzeigetext
    public String getLabel() {
        return label;
    }
}
