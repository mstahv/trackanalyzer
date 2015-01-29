package org.example.backend;

import java.util.List;
import javax.inject.Inject;
import org.vaadin.viritin.LazyList;

public class LazyUpdateList<Update> extends LazyList<Update> {

    @Inject
    public LazyUpdateList(GPSRouteService service) {
        super(new EntityProvider<Update>() {

            @Override
            public List findEntities(int index) {
                return service.find(index, 15);
            }

            @Override
            public int size() {
                return (int) service.getEntityCount();
            }
        }, 15);
    }

}
