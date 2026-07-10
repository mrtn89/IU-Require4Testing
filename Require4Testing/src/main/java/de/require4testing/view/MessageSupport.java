package de.require4testing.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Abstrakte Basisklasse für Beans, die FacesMessages ausgeben möchten.
 * Stellt Hilfsmethoden für Info- und Fehlermeldungen bereit.
 */
public abstract class MessageSupport {

	//Fügt eine Info-Nachricht dem FacesContext hinzu
    protected void info(String summary) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, summary));
    }

    //Fügt eine Fehler-Nachricht dem FacesContext hinzu
    protected void error(String summary) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, summary));
    }
}
