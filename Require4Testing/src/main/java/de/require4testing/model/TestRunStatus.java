package de.require4testing.model;

//Statuswerte für Testläufe
public enum TestRunStatus {
    GEPLANT("Geplant"),
    AKTIV("Aktiv"),
    ABGESCHLOSSEN("Abgeschlossen");

	// Anzeigetext des Status
    private final String label;

    TestRunStatus(String label) {
        this.label = label;
    }
    
    // Liefert den Anzeigetext
    public String getLabel() {
        return label;
    }
}
