package de.require4testing.model;

//Mögliche Ergebnisse einer Testausführung
public enum ExecutionResult {
    OFFEN("Offen"),
    BESTANDEN("Bestanden"),
    FEHLGESCHLAGEN("Fehlgeschlagen");

	// Anzeigetext für die Benutzeroberfläche
    private final String label;

    ExecutionResult(String label) {
        this.label = label;
    }

    // Liefert den Anzeigetext des Enum-Werts
    public String getLabel() {
        return label;
    }
}
