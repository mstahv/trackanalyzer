package org.example.strategies;

import com.vaadin.data.provider.BackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.server.SerializableFunction;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import java.util.stream.Stream;
import org.example.backend.GPSRouteService;
import org.example.backend.Update;

import javax.inject.Inject;

public class ServiceWiringStrategy implements TableStrategy {

    @Inject
    GPSRouteService s;

    @Override
    public Component getTable() {
        Grid<Update> entryList = new Grid();
        entryList.addColumn(u -> u.getAltitude().toString()).setCaption("Altitude");
        entryList.addColumn(u -> u.getCharging().toString()).setCaption("Charging");
        entryList.addColumn(u -> u.getFixCount().toString()).setCaption("Fix Count");
        entryList.addColumn(u -> u.getLon().toString()).setCaption("Lon");
        entryList.addColumn(u -> u.getSpeed().toString()).setCaption("Speed");
        entryList.addColumn(u -> u.getSignalLevel().toString()).setCaption("Signal Level");
        entryList.addColumn(u -> u.getValid().toString()).setCaption("Valid");
        entryList.addColumn(u -> u.getCourse().toString()).setCaption("Course");
        entryList.addColumn(u -> u.getImei()).setCaption("Imei");
        entryList.addColumn(u -> u.getId() + "").setCaption("Id");
        entryList.addColumn(u -> u.getLat().toString()).setCaption("Lat");
        entryList.addColumn(u -> u.getBatteryLevel().toString()).setCaption("Battery Level");
        entryList.addColumn(u -> u.getTimestamp().toString()).setCaption("Timestamp");

        BackEndDataProvider<Update, Object> dataProvider = new BackEndDataProvider<>(
                (Query<Update, Object> t) -> s.find(t.getOffset(), t.getLimit()).stream(), 
                (Query<Update, Object> t) -> s.getEntityCount()
        );

        entryList.setDataProvider(new BackEndDataProvider<>(
                t -> s.find(t.getOffset(), t.getLimit()).stream(), 
                t -> s.getEntityCount()
        ));
        entryList.setSizeFull();

        return entryList;
    }
}
