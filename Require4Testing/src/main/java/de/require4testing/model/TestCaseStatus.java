package de.require4testing.model;

//Statuswerte für Testfälle
public enum TestCaseStatus {
    ENTWURF("Entwurf"),
    BEREIT("Bereit"),
    GESPERRT("Gesperrt");

	// Anzeigetext des Status
    private final String label;

    TestCaseStatus(String label) {
        this.label = label;
    }

    // Liefert den Anzeigetext
    public String getLabel() {
        return label;
    }
}
