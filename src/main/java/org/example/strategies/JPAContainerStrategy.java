package org.example.strategies;

import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

import javax.inject.Inject;

public class JPAContainerStrategy implements TableStrategy {

    @Inject
    UpdateJPAContainer container;

    @Override
    public Component getTable() {
        Table table = new Table();
        table.setContainerDataSource(container);
        table.setSizeFull();
        return table;
    }
}
