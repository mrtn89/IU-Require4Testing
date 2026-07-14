## Require4Testing

Maven-Webprojekt für Java 8, JSF 2.2, PrimeFaces 8, JPA/Hibernate, CDI, MySQL 8 und Apache TomEE.

### Projektstruktur
- src/main/java: Java-Code mit Entities, Services und CDI-/JSF-Beans
- src/main/resources/META-INF/persistence.xml: JPA-Konfiguration
- src/main/webapp: XHTML-Seiten, Facelets-Template, CSS und web.xml
- sql/create-database.sql: Datenbank anlegen

### Anwendung aufsetzen
- MySQL-Datenbank mit sql/create-database.sql anlegen.
- Zugangsdaten in src/main/resources/META-INF/persistence.xml prüfen und bei Bedarf anpassen.
- Projekt als Maven-Projekt in Eclipse importieren.
- Apache TomEE als Server in Eclipse konfigurieren.
- Projekt auf TomEE deployen.
- Anwendung unter http://localhost:8080/require4testing/overview.xhtml öffnen.
