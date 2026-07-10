package de.require4testing.view;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

//Session-scoped Bean zur Verwaltung der Rollenauswahl im Anwendungskopf.
@Named
@SessionScoped
public class AppBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // Aktuell ausgewählte Rolle, Standardwert ist Requirements Engineer
    private String selectedRole = "Requirements Engineer";

    //Liefert die zur Auswahl stehenden Rollen.
    public List<String> getAvailableRoles() {
        return Arrays.asList("Requirements Engineer", "Testmanager:in", "Testfallersteller:in", "Tester:in");
    }

    //Gibt die aktuell ausgewählte Rolle zurück.
    public String getSelectedRole() {
        return selectedRole;
    }

    //Setzt die ausgewählte Rolle aus dem Dropdown-Menü.
    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }

    /** Aktionsmethode: übernimmt die gewählte Rolle in die Session. 
     * Ist als Platzhalter zu sehen bis eine entsprechende Anforderung eingeht für die Umsetzung.*/
    public void applyRole() {
    }
}
