package org.example.strategies;

import com.vaadin.ui.Component;
import org.example.backend.GPSRouteService;

import javax.inject.Inject;
import org.vaadin.viritin.grid.MGrid;

public class ServiceWiringStrategyWithMGrid implements TableStrategy {

    @Inject
    GPSRouteService s;

    @Override
    public Component getTable() {
        return new MGrid<>(s::fetchUpdates, s::getEntityCount)
                .withFullWidth();
    }
}
