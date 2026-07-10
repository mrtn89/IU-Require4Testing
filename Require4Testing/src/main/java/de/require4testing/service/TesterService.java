package de.require4testing.service;

import de.require4testing.model.Tester;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class TesterService implements Serializable {

    private static final long serialVersionUID = 1L;

    // Zugriff auf den Persistenzkontext
    @Inject
    private EntityManager entityManager;

    // Speichert einen neuen Tester
    public void create(Tester tester) {
        entityManager.getTransaction().begin();
        entityManager.persist(tester);
        entityManager.getTransaction().commit();
    }

    // Sucht einen Tester anhand der ID
    public Tester find(Long id) {
        return entityManager.find(Tester.class, id);
    }

    // Liefert alle Tester alphabetisch sortiert
    public List<Tester> findAll() {
        TypedQuery<Tester> query = entityManager.createQuery(
                "select t from Tester t order by t.lastName asc, t.firstName asc", Tester.class);
        return query.getResultList();
    }
}
