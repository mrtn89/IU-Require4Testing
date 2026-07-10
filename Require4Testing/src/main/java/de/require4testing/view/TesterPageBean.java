package de.require4testing.view;

import de.require4testing.model.Tester;
import de.require4testing.service.TesterService;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * View-scoped Bean für die Tester-Seite.
 * Verwaltet das Anlegen, Speichern und Auflisten von Testern.
 */
@Named
@ViewScoped
public class TesterPageBean extends MessageSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TesterService testerService;

    private Tester tester = new Tester();

    //Speichert den Tester, zeigt Info an und resetet das Formular.
    public void save() {
        testerService.create(tester);
        info("Tester:in wurde gespeichert.");
        tester = new Tester();
    }

    //Gibt das aktuelle Tester-Objekt für das Formular zurück.
    public Tester getTester() {
        return tester;
    }
    
    //Setzt das aktuelle Tester-Objekt.
    public void setTester(Tester tester) {
        this.tester = tester;
    }
    
    //Liefert alle Tester für die Übersichtstabelle.
    public List<Tester> getTesters() {
        return testerService.findAll();
    }
}
