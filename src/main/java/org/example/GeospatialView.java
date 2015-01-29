package org.example;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.example.backend.GPSRouteService;
import org.example.backend.Update;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.addon.leaflet.LMap;
import org.vaadin.addon.leaflet.LOpenStreetMapLayer;
import org.vaadin.addon.leaflet.LPolyline;
import org.vaadin.addon.leaflet.shared.Point;
import org.vaadin.viritin.layouts.MHorizontalLayout;

@CDIView
@ViewMenuItem(icon = FontAwesome.LIFE_BOUY, order = ViewMenuItem.END)
public class GeospatialView extends MVerticalLayout implements View {

    @Inject
    GPSRouteService service;

    LMap map = new LMap();
    LPolyline snake;

    DateField start = new DateField("From");
    DateField end = new DateField("To");

    static final long DAY = 24 * 60 * 60 * 1000;
    static final long ONE_WEEK = 7 * DAY;
    static final Date periodStart = new Date(2014 - 1900, 6 - 1, 15);
    static final Date periodEnd = new Date(2014 - 1900, 10 - 1, 15);

    @PostConstruct
    void init() {
        start.setRangeStart(periodStart);
        start.setRangeEnd(periodEnd);
        end.setRangeStart(periodStart);
        end.setRangeEnd(periodEnd);
        start.setValue(periodStart);
        end.setValue(new Date(periodStart.getTime() + ONE_WEEK));
        start.addValueChangeListener(e -> {
            long maxEnd = start.getValue().getTime() + ONE_WEEK;
            if (end.getValue().getTime() > maxEnd) {
                end.setValue(new Date(maxEnd));
            } else if (end.getValue().getTime() < start.getValue().getTime()) {
                end.setValue(new Date(start.getValue().getTime() + DAY));
            }
            drawRoute();
        });
        end.addValueChangeListener(e -> {
            long minStart = end.getValue().getTime() - ONE_WEEK;
            if (start.getValue().getTime() < minStart) {
                start.setValue(new Date(minStart));
            } else if (start.getValue().getTime() > end.getValue().getTime()) {
                start.setValue(new Date(end.getValue().getTime() - DAY));
            }
            drawRoute();
        });

        add(new MHorizontalLayout(start, end));

        map.addLayer(new LOpenStreetMapLayer());
        expand(map);
        drawRoute();
    }

    private void drawRoute() {
        List<Update> updates = service.fetchUpdates(start.getValue(), end.
                getValue());
        if (!updates.isEmpty()) {
            List<Point> points = updates.stream()
                    .map(u -> new Point(u.getLat(), u.getLon()))
                    .collect(Collectors.toList());
            if (snake != null) {
                snake.setPoints(points.toArray(new Point[points.size()]));
            } else {
                snake = new LPolyline(points.toArray(new Point[points.size()]));
                map.addLayer(snake);
            }
            map.zoomToContent();
        } else {
            Notification.show("No points found from the selected perioud");
            if (snake != null) {
                map.removeLayer(snake);
                snake = null;
            }
            
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
