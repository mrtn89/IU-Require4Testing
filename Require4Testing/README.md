# Require4Testing

Maven-Webprojekt für Java 8, JSF 2.2, PrimeFaces 8, JPA/Hibernate, CDI, MySQL 8 und Apache TomEE.

## Umgesetzte User Stories

- MUST: Anforderungen erstellen.
- MUST: Testläufe anlegen.
- MUST: Testfälle zu Anforderungen erstellen.
- SHOULD: Testlauf verschiedene Testfälle und Tester:in zuordnen.
- SHOULD: Zugeordnete Testfälle mit Ergebnis versehen.

## Projektstruktur

- `src/main/java`: Java-Code mit Entities, Services und CDI-/JSF-Beans
- `src/main/resources/META-INF/persistence.xml`: JPA-Konfiguration
- `src/main/webapp`: XHTML-Seiten, Facelets-Template, CSS und `web.xml`
- `sql/create-database.sql`: Datenbank anlegen
- `docs/original`: übernommene Ausgangsdateien `index.html` und `style.css`

## Start in Eclipse und TomEE

1. MySQL-Datenbank mit `sql/create-database.sql` anlegen.
2. Zugangsdaten in `src/main/resources/META-INF/persistence.xml` prüfen und bei Bedarf anpassen.
3. Projekt als Maven-Projekt in Eclipse importieren.
4. Apache TomEE als Server in Eclipse konfigurieren.
5. Projekt auf TomEE deployen.
6. Anwendung unter `http://localhost:8080/require4testing/overview.xhtml` öffnen.

## Hinweise

- Die Oberfläche übernimmt das bereitgestellte Layout und ergänzt es um Formulare und Tabellen.
- Eine Login-Funktion ist bewusst nicht enthalten; stattdessen kann die Rolle oben gewählt werden.
- Für die Zuweisung von Testläufen sollten zuerst Tester:innen und Testfälle angelegt werden.


## Version v2

- `executions.xhtml` ohne problematischen ternären Ausdruck im `class`-Attribut.
- `docs/original/index.html` entfernt, damit Eclipse dort keine HTML-Fehler meldet.
- `.settings/org.eclipse.wst.validation.prefs` ergänzt, damit Eclipse-Projektvalidatoren für dieses Projekt nicht anschlagen.


## Version v3

- `EntityManagerProducer` erzeugt den `EntityManagerFactory` jetzt lazy beim ersten Zugriff statt bereits in `@PostConstruct`.
- `persistence.xml` enthält `hibernate.delay_cdi_access=true`, um Hibernate/CDI-Initialisierungsprobleme in TomEE zu entschärfen.
