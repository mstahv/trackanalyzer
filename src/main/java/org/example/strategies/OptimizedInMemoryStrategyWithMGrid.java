package org.example.strategies;

import com.vaadin.ui.Component;
import org.example.backend.GPSRouteService;

import javax.inject.Inject;
import org.vaadin.viritin.grid.MGrid;

public class OptimizedInMemoryStrategyWithMGrid implements TableStrategy {

    @Inject
    GPSRouteService service;

    @Override
    public Component getTable() {
        /**
         * ListContainer used by  MTable (stuff from Viritin add-on) is a
         * well optimized in memory solution. Almost no overhead the plain
         * List of entities (as opposed ot multiple times of the core
         * BeanItemContainer that contains huge set of features).
         */
        return new MGrid<>(service.findAll())
                .withFullWidth();
    }
}
