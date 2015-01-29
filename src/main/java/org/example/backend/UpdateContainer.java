
package org.example.backend;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Matti Tahvonen
 */
public class UpdateContainer extends JPAContainer {
    
    @PersistenceContext(unitName = "tracker")
    EntityManager em;

    public UpdateContainer() {
        super(Update.class);
    }
    
    @PostConstruct
    void init() {
        setEntityProvider(new CachingLocalEntityProvider(Update.class, em));
    }

}
