package org.example.strategies;

import org.example.backend.GPSRouteService;
import org.vaadin.viritin.LazyList;

import javax.inject.Inject;
import java.util.List;

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
