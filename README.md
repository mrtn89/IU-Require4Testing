# Require4Testing

Maven-Webprojekt für Java 8, JSF 2.2, PrimeFaces 8, JPA/Hibernate, CDI, MySQL 8 und Apache TomEE.

### Projektstruktur
- src/main/java: Java-Code mit Entities, Services und CDI-/JSF-Beans
- src/main/resources/META-INF/persistence.xml: JPA-Konfiguration
- src/main/webapp: XHTML-Seiten, Facelets-Template, CSS und web.xml
- sql/create-database.sql: Datenbank anlegen


## Voraussetzungen
- Java Development Kit (JDK), im Projekt wurde Java 8 verwendet.
- Eclipse IDE for Enterprise Java and Web Developers
- Apache TomEE, im Projekt wurde Version plus-8.0.16 verwendet.
- MySQL Server 8 oder kompatibel
- Maven (in Eclipse integriert oder lokal installiert)

## Datenbank einrichten

1. Einen lokalen MySQL-Server starten.
2. Das Datenbankschema mithilfe des Skripts anlegen:

   ```sql
   mysql -u <benutzername> -p < sql/create-database.sql
   ```
3. Datenbank Benutzer und Passwort sind in src/main/resources/META-INF/persistence.xml zu finden.

## Projekt importieren

1. Das Repository klonen oder als ZIP-Datei herunterladen und entpacken.
2. In Eclipse **File → Import → Maven → Existing Maven Projects** wählen.
3. Das Projektverzeichnis auswählen und den Import abschließen.
4. Falls erforderlich: Rechtsklick auf das Projekt → **Maven → Update Project**.

## TomEE konfigurieren

1. In Eclipse die Ansicht **Servers** öffnen.
2. Über **New → Server** einen Apache-TomEE-Server hinzufügen.
3. Das lokale TomEE-Installationsverzeichnis auswählen.
4. Das Projekt `require4testing` dem Server hinzufügen.

## Anwendung starten

1. Den TomEE-Server in Eclipse starten.
2. Prüfen, ob das Projekt erfolgreich auf TomEE veröffentlicht wurde.
3. Die Anwendung im Browser öffnen:

   ```text
   http://localhost:8080/require4testing/overview.xhtml
   ```
