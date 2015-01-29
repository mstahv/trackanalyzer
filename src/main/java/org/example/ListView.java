package org.example;

import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.UIScoped;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.example.backend.GPSRouteService;
import org.example.backend.Update;
import org.vaadin.viritin.LazyList;
import org.vaadin.viritin.LazyList.CountProvider;
import org.vaadin.viritin.LazyList.PagingProvider;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.layouts.MVerticalLayout;

@UIScoped
@CDIView("")
public class ListView extends MVerticalLayout implements View {

//    @Inject
//    UpdateContainer container;
//    @Inject
//    LazyUpdateContainer lazyUpdateContainer;
//    @Inject
//    LazyUpdateList lazyUpdateList;
//    // Instantiate and configure a Table to list PhoneBookEntries
//    Table entryList = new Table();

    MTable<Update> table;

    @Inject
    GPSRouteService s;

    @PostConstruct
    void init() {
        add(table = new MTable<>(s::fetchUpdates, s::getEntityCount));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
