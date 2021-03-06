package org.example.strategies;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import org.example.backend.GPSRouteService;
import org.example.backend.Update;

import javax.inject.Inject;

public class BasicInMemoryStrategy implements TableStrategy {

    @Inject
    GPSRouteService service;

    @Override
    public Component getTable() {
        Table entryList = new Table();
        BeanItemContainer<Update> container = new BeanItemContainer<>(Update.class,service.findAll());
        entryList.setContainerDataSource(container);
        entryList.setSizeFull();
        return entryList;
    }
}
