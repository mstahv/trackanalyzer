package org.example.strategies;

import com.vaadin.ui.Component;
import org.example.backend.GPSRouteService;
import org.example.backend.Update;
import org.vaadin.viritin.fields.MTable;

import javax.inject.Inject;

public class ServiceWiringStrategy implements TableStrategy {

    @Inject
    GPSRouteService s;

    @Override
    public Component getTable() {
        return new MTable<Update>(s::fetchUpdates, s::getEntityCount)
                .withFullWidth();
    }
}
