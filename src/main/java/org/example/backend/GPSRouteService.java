package org.example.backend;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.vaadin.viritin.LazyList;

/**
 * EJB to hide JPA related stuff from the UI layer.
 */
@Stateless
public class GPSRouteService {

    @PersistenceContext(unitName = "tracker")
    EntityManager em;

    public GPSRouteService() {
    }

    public List<Update> findAll() {
        return em.
                createQuery("SELECT u FROM Update u ORDER BY u.timestamp DESC",
                        Update.class).getResultList();
    }

    public List<Update> find(int startIndex, int maxResults) {
        return em.
                createQuery("SELECT u FROM Update u ORDER BY u.timestamp DESC",
                        Update.class)
                .setFirstResult(startIndex)
                .setMaxResults(maxResults)
                .getResultList();
    }

    public List<Update> fetchUpdates(int startIndex) {
        return em.
                createQuery("SELECT u FROM Update u ORDER BY u.timestamp DESC",
                        Update.class)
                .setFirstResult(startIndex)
                .setMaxResults(LazyList.DEFAULT_PAGE_SIZE)
                .getResultList();
    }

    public List<Update> fetchUpdates(Date from, Date to) {
        return em.
                createQuery("SELECT u FROM Update u "
                        + "WHERE u.timestamp BETWEEN :from AND :to "
                        + " "
                        , Update.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    public int getEntityCount() {
        return em.createQuery("SELECT COUNT(u) FROM Update u", Long.class).
                getSingleResult().intValue();
    }

}
