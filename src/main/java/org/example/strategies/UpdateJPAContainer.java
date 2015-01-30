
package org.example.strategies;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;
import org.example.backend.Update;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Matti Tahvonen
 */
public class UpdateJPAContainer extends JPAContainer {
    
    @PersistenceContext(unitName = "tracker")
    EntityManager em;

    public UpdateJPAContainer() {
        super(Update.class);
    }
    
    @PostConstruct
    void init() {
        setEntityProvider(new CachingLocalEntityProvider(Update.class, em));
    }

}
