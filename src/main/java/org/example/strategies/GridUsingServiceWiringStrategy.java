package org.example.strategies;

import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import org.example.backend.GPSRouteService;

import javax.inject.Inject;
import org.vaadin.viritin.LazyList;
import org.vaadin.viritin.ListContainer;

public class GridUsingServiceWiringStrategy implements TableStrategy {

    @Inject
    GPSRouteService s;

    @Override
    public Component getTable() {

        // Small experiment to use the upcoming Grid component
        Grid grid = new Grid();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setContainerDataSource(new ListContainer(new LazyList<>(s::fetchUpdates, s::getEntityCount, 45)){

            @Override
            public void sort(Object[] propertyId, boolean[] ascending) {
                // f#ck that shit :-)
            }
            
        });
                
        return grid;
    }
}
