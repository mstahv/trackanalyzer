package org.example;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.example.strategies.BasicInMemoryStrategy;
import org.example.strategies.OptimizedInMemoryStrategy;
import org.example.strategies.OptimizedInMemoryStrategyWithMGrid;
import org.example.strategies.ServiceWiringStrategy;
import org.example.strategies.ServiceWiringStrategyWithMGrid;
import org.vaadin.viritin.layouts.MVerticalLayout;

@CDIUI("")
@Theme("valo")
@Title("GPS analyzer")
public class VaadinUI extends UI {

    @Inject
    transient OptimizedInMemoryStrategyWithMGrid strategy;


    @Override
    protected void init(VaadinRequest request) {
        MVerticalLayout layout = new MVerticalLayout();
        layout.expand(strategy.getTable());
        Button button = new Button("Estimate session size");
        button.addClickListener(e -> {
            VaadinSession current = VaadinSession.getCurrent();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(current);
                Notification.show("Estimated session size:" + byteArrayOutputStream.size(), Notification.Type.WARNING_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(VaadinUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        layout.add(button);
        setContent(layout);
    }

}
