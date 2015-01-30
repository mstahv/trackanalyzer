package org.example;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.example.strategies.ServiceWiringStrategy;

@CDIView
public class ListView extends MVerticalLayout implements View {

//    @Inject
//    BasicInMemoryStrategy strategy;
//    @Inject
//    OptimizedInMemoryStrategy strategy;
//    @Inject
//    JPAContainerStrategy strategy;
//    @Inject
//    LazyQueryContainerStrategy strategy;

    @Inject
    ServiceWiringStrategy strategy;

    @PostConstruct
    void init() {
        expand(strategy.getTable());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
